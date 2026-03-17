package rtrModGui;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainGUI extends JFrame {
    private DefaultListModel<ModInfo> modListModel;
    private JList<ModInfo> modList;
    private JTextArea logArea;

    public MainGUI() {
        initComponents();
        setupDragAndDrop();
        refreshMods();
        setTitle("Rise to Ruins Mod Loader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton launchButton = new JButton("🚀 Start game");
        JButton refreshButton = new JButton("🔄 Refresh mods");
        JButton enableAllButton = new JButton("✅ Enable all");
        JButton disableAllButton = new JButton("❌ Disable all");

        topPanel.add(launchButton);
        topPanel.add(refreshButton);
        topPanel.add(enableAllButton);
        topPanel.add(disableAllButton);
        add(topPanel, BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("📦 Installed Mods"));

        modListModel = new DefaultListModel<>();
        modList = new JList<>(modListModel);
        modList.setCellRenderer(new ModListRenderer());
        modList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        modList.setComponentPopupMenu(getJPopupMenu());

        leftPanel.add(new JScrollPane(modList), BorderLayout.CENTER);

        JLabel dropLabel = new JLabel("📂 Drop .zip files here", JLabel.CENTER);
        dropLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        dropLabel.setPreferredSize(new Dimension(0, 40));
        leftPanel.add(dropLabel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("📟 Log"));

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        rightPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);

        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);
        splitPane.setDividerLocation(300);

        add(splitPane, BorderLayout.CENTER);

        launchButton.addActionListener(e -> launchGame());
        refreshButton.addActionListener(e -> refreshMods());
        enableAllButton.addActionListener(e -> toggleAllMods(true));
        disableAllButton.addActionListener(e -> toggleAllMods(false));
    }

    private JPopupMenu getJPopupMenu() {
        JPopupMenu modMenu = new JPopupMenu();
        JMenuItem enableItem = new JMenuItem("Enable");
        JMenuItem disableItem = new JMenuItem("Disable");
        JMenuItem deleteItem = new JMenuItem("Delete");

        enableItem.addActionListener(e -> toggleSelectedMods(true));
        disableItem.addActionListener(e -> toggleSelectedMods(false));
        deleteItem.addActionListener(e -> deleteSelectedMods());

        modMenu.add(enableItem);
        modMenu.add(disableItem);
        modMenu.addSeparator();
        modMenu.add(deleteItem);
        return modMenu;
    }

    private void setupDragAndDrop() {
        DropTarget dropTarget = new DropTarget() {
            @Override
            @SuppressWarnings("unchecked")
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    Transferable transferable = evt.getTransferable();
                    if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        List<File> files = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                        for (File file : files) {
                            installMod(file);
                        }
                    }
                } catch (Exception e) {
                    log("❌ Drag & drop error: " + e.getMessage());
                }
            }
        };
        this.setDropTarget(dropTarget);
    }

    private void installMod(File file) {
        log("📦 Installing: " + file.getName());
        String baseName = file.getName().replace(".zip", "");
        File modFolder = new File("mods/" + baseName);
        if (modFolder.exists()) {
            int choice = JOptionPane.showConfirmDialog(this, "A mod named '" + baseName + "' already exists." +
                    "\nOverwrite?", "Mod already exists", JOptionPane.YES_NO_OPTION);
            if (choice != JOptionPane.YES_OPTION) {
                log("⏭️ Installation cancelled");
                return;
            } else {
                FileUtils.deleteDirectory(modFolder);
            }
        }
        if (ModScanner.installMod(file)) {
            log("✅ Installed successfully");
            refreshMods();
        } else {
            log("❌ Installation failed");
        }
    }

    private void refreshMods() {
        List<ModInfo> mods = ModScanner.scanMods();
        modListModel.clear();
        for (ModInfo mod : mods) {
            modListModel.addElement(mod);
        }
        log("🔄 Found " + mods.size() + " mod(s)");
    }

    private void toggleSelectedMods(boolean enable) {
        for (ModInfo mod : modList.getSelectedValuesList()) {
            mod.setEnabled(enable);
        }
        modList.repaint();
    }

    private void toggleAllMods(boolean enable) {
        for (int i = 0; i < modListModel.size(); i++) {
            modListModel.get(i).setEnabled(enable);
        }
        modList.repaint();
    }

    private void deleteSelectedMods() {
        int confirm = JOptionPane.showConfirmDialog(this, "Delete selected mods?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            for (ModInfo mod : modList.getSelectedValuesList()) {
                File modFolder = new File(mod.getPath());
                FileUtils.deleteDirectory(modFolder);
                log("🗑️ Deleted: " + mod.getName());
            }
            refreshMods();
        }
    }

    private void launchGame() {
        log("🚀 Starting game...");
        List<ModInfo> enabledMods = new ArrayList<>();
        for (int i = 0; i < modListModel.size(); i++) {
            ModInfo mod = modListModel.get(i);
            if (mod.isEnabled()) {
                enabledMods.add(mod);
            }
        }
        new Thread(() -> {
            try {
                GameLauncher.launch(enabledMods, new GameLauncher.GameLauncherCallback() {
                    @Override
                    public void onGameStarting() {
                        SwingUtilities.invokeLater(() -> log("🎮 Game is running..."));
                    }
                    @Override
                    public void onGameFinished() {
                        SwingUtilities.invokeLater(() -> log("✅ Game stopped"));
                    }
                    @Override
                    public void onGameError(Exception e) {
                        SwingUtilities.invokeLater(() -> log("❌ Error: " + e.getMessage()));
                    }
                });
            } catch (Exception e) {
                log("❌ Launch error: " + e.getMessage());
            }
        }).start();
    }

    private void log(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append(message + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }

    private static class ModListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            ModInfo mod = (ModInfo) value;
            JLabel label = (JLabel) super.getListCellRendererComponent(list, mod.toString(), index, isSelected, cellHasFocus);
            if (!mod.isEnabled()) {
                label.setForeground(Color.GRAY);
            }
            return label;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}