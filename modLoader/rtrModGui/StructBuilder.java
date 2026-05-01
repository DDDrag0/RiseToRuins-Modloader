package rtrModGui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StructBuilder extends JFrame {
    private JTextField classNameField, baseFileNameField, descField, baseNameField;
    private JSpinner widthSpinner, heightSpinner;
    private JTextField colorField;
    private JSpinner upgradeCountSpinner;
    private JPanel upgradesPanel;
    private List<UpgradePanel> upgradePanels = new ArrayList<>();

    public StructBuilder() {
        setTitle("Structure Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        addField(mainPanel, gbc, row++, "ClassName:", classNameField = new JTextField("Torre"));
        addField(mainPanel, gbc, row++, "MiniMap color (int):", colorField = new JTextField("-16719391"));
        addField(mainPanel, gbc, row++, "Object width:", widthSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 512, 1)));
        addField(mainPanel, gbc, row++, "Object height:", heightSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 512, 1)));
        addField(mainPanel, gbc, row++, "Image File Name:", baseFileNameField = new JTextField("ImageName"));
        addField(mainPanel, gbc, row++, "Description:", descField = new JTextField("Descrizione della struttura"));
        addField(mainPanel, gbc, row++, "Nome base (livello 0):", baseNameField = new JTextField("Nome della struttura"));

        // Upgrade count
        JPanel countPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        countPanel.add(new JLabel("Numero upgrade (0 = senza upgrade):"));
        upgradeCountSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
        countPanel.add(upgradeCountSpinner);
        JButton refreshBtn = new JButton("Aggiorna pannelli upgrade");
        refreshBtn.addActionListener(e -> refreshUpgradePanels());
        countPanel.add(refreshBtn);
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        mainPanel.add(countPanel, gbc);
        gbc.gridwidth = 1;

        // Upgrade panel container
        upgradesPanel = new JPanel();
        upgradesPanel.setLayout(new BoxLayout(upgradesPanel, BoxLayout.Y_AXIS));
        upgradesPanel.setBorder(new TitledBorder("Livelli di upgrade"));
        JScrollPane scrollUpgrades = new JScrollPane(upgradesPanel);
        scrollUpgrades.setPreferredSize(new Dimension(800, 200));
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        mainPanel.add(scrollUpgrades, gbc);

        // Generate button
        JButton generateBtn = new JButton("Generate .java");
        generateBtn.addActionListener(e -> generateJavaFile());
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(generateBtn);

        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(900, 700);
        setLocationRelativeTo(null);
        refreshUpgradePanels();
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void refreshUpgradePanels() {
        upgradesPanel.removeAll();
        upgradePanels.clear();
        int count = (Integer) upgradeCountSpinner.getValue();
        for (int i = 0; i < count; i++) {
            UpgradePanel up = new UpgradePanel(i);
            upgradePanels.add(up);
            upgradesPanel.add(up);
        }
        upgradesPanel.revalidate();
        upgradesPanel.repaint();
    }

    private void generateJavaFile() {
        try {
            String className = classNameField.getText().trim();
            if (className.isEmpty()) throw new Exception("Nome classe vuoto");

            int upgradeCount = (Integer) upgradeCountSpinner.getValue();
            String color = colorField.getText().trim();
            int objWidth = (Integer) widthSpinner.getValue();
            int objHeight = (Integer) heightSpinner.getValue();
            String baseFileName = baseFileNameField.getText().trim();
            String description = descField.getText().trim();
            String baseName = baseNameField.getText().trim();

            // Raccolta dati upgrade
            List<UpgradeData> upgrades = new ArrayList<>();
            for (int i = 0; i < upgradeCount; i++) {
                UpgradePanel up = upgradePanels.get(i);
                String name = up.nameField.getText().trim();
                String suffix = up.suffixField.getText().trim();
                upgrades.add(new UpgradeData(name, suffix));
            }

            String codice;
            if (upgradeCount == 0) {
                codice = generateNoUpgrade(className, color, objWidth, objHeight, baseFileName, description, baseName);
            } else {
                codice = generateWithUpgrades(className, color, objWidth, objHeight, baseFileName, description, upgrades);
            }

            // Salvataggio file
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new java.io.File(className + ".java"));
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                Path path = fileChooser.getSelectedFile().toPath();
                Files.write(path, codice.getBytes(StandardCharsets.UTF_8));
                JOptionPane.showMessageDialog(this, "File generato con successo:\n" + path.toAbsolutePath());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generateNoUpgrade(String className, String color, int objWidth, int objHeight, String fileName, String description, String name) {
        return "package rtr.map.tileSets.objects;\n\n" +
                "import org.newdawn.slick.Color;\n" +
                "import rtr.font.Text;\n" +
                "import rtr.map.MapTilesLoader;\n" +
                "import rtr.map.tileSets.TileSetBase;\n\n" +
                "public class " + className + "\n" +
                "extends TileSetBase {\n" +
                "    public " + className + "(int tGID) {\n" +
                "        super(tGID, MapTilesLoader.TileSetType.OBJECT);\n" +
                "    }\n\n" +
                "    @Override\n" +
                "    public void initialize() {\n" +
                "        this.miniMapColor = new Color(" + color + ");\n" +
                "        this.layer = (byte)11;\n" +
                "        this.objectWidth = " + objWidth + ";\n" +
                "        this.objectHeight = " + objHeight + ";\n" +
                "        this.fileName = \"" + fileName + "\";\n" +
                "        this.name = Text.getText(\"" + name + "\");\n" +
                "        this.description = Text.getText(\"" + description + "\");\n" +
                "    }\n" +
                "}\n";
    }

    private String generateWithUpgrades(String className, String color, int objWidth, int objHeight, String baseFileName, String description, List<UpgradeData> upgrades) {
        StringBuilder initSwitch = new StringBuilder();
        initSwitch.append("switch (this.upgradePhase) {\n");
        for (int i = 0; i < upgrades.size(); i++) {
            UpgradeData ud = upgrades.get(i);
            initSwitch.append("            case ").append(i).append(": {\n");
            initSwitch.append("                this.name = Text.getText(\"").append(ud.name).append("\");\n");
            if (i != 0 && ud.suffix != null && !ud.suffix.isEmpty()) {
                initSwitch.append("                this.fileName = this.fileName + \"").append(ud.suffix).append("\";\n");
            }
            initSwitch.append("                break;\n");
            initSwitch.append("            }\n");
        }
        initSwitch.append("        }");

        return "package rtr.map.tileSets.objects;\n\n" +
                "import org.newdawn.slick.Color;\n" +
                "import rtr.font.Text;\n" +
                "import rtr.map.MapTilesLoader;\n" +
                "import rtr.map.tileSets.TileSetBase;\n\n" +
                "public class " + className + "\n" +
                "extends TileSetBase {\n" +
                "    public " + className + "(int tGID, int uP) {\n" +
                "        super(tGID, uP, MapTilesLoader.TileSetType.OBJECT);\n" +
                "    }\n\n" +
                "    @Override\n" +
                "    public void initialize() {\n" +
                "        this.miniMapColor = new Color(" + color + ");\n" +
                "        this.layer = (byte)11;\n" +
                "        this.objectWidth = " + objWidth + ";\n" +
                "        this.objectHeight = " + objHeight + ";\n" +
                "        this.fileName = \"" + baseFileName + "\";\n" +
                "        this.description = Text.getText(\"" + description + "\");\n" +
                "        " + initSwitch + "\n" +
                "    }\n" +
                "}\n";
    }

    // Pannello per un singolo livello di upgrade
    class UpgradePanel extends JPanel {
        JTextField nameField, suffixField;
        UpgradePanel(int level) {
            setLayout(new FlowLayout(FlowLayout.LEFT));
            setBorder(BorderFactory.createEtchedBorder());
            add(new JLabel("Livello " + level + " - Nome:"));
            nameField = new JTextField("Nome struttura livello " + (level+1), 20);
            add(nameField);
            add(new JLabel("Suffisso file:"));
            suffixField = new JTextField("Upgrade" + (level+1), 20);
            add(suffixField);
        }
    }

    private static class UpgradeData {
        final String name;
        final String suffix;
        UpgradeData(String name, String suffix) {
            this.name = name;
            this.suffix = suffix;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StructBuilder().setVisible(true));
    }
}