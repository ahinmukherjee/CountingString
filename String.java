JMenuBar menuBar = new JMenuBar();

JMenu fd = new JMenu("FD");
JMenu rd = new JMenu("RD");

// Menu bar style
menuBar.setBackground(new Color(30, 30, 30));
menuBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
menuBar.setPreferredSize(new Dimension(600, 45));

// FD style
fd.setForeground(Color.WHITE);
fd.setBackground(new Color(30, 30, 30));
fd.setFont(new Font("Calibri", Font.BOLD, 16));
fd.setOpaque(true);
fd.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

// RD style
rd.setForeground(Color.WHITE);
rd.setBackground(new Color(30, 30, 30));
rd.setFont(new Font("Calibri", Font.BOLD, 16));
rd.setOpaque(true);
rd.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

menuBar.add(fd);
menuBar.add(rd);

setJMenuBar(menuBar);

```java
class UserClass extends JFrame {

    CardLayout cardLayout;
    JPanel mainPanel;

    public UserClass() {
        setSize(600, 450);
        setLocationRelativeTo(null);
        setTitle("User");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel: switches between FD and RD pages
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new FDPanel(), "FD");
        mainPanel.add(new RDPanel(), "RD");

        // Menu bar
        JMenuBar menuBar = new JMenuBar();

        JMenu accountMenu = new JMenu("Account");

        JRadioButtonMenuItem fdItem = new JRadioButtonMenuItem("FD");
        JRadioButtonMenuItem rdItem = new JRadioButtonMenuItem("RD");

        // Only one item can be selected at a time
        ButtonGroup group = new ButtonGroup();
        group.add(fdItem);
        group.add(rdItem);

        accountMenu.add(fdItem);
        accountMenu.add(rdItem);

        menuBar.add(accountMenu);
        setJMenuBar(menuBar);

        // FD selected
        fdItem.addActionListener(e -> {
            cardLayout.show(mainPanel, "FD");
            System.out.println("FD selected");
        });

        // RD selected
        rdItem.addActionListener(e -> {
            cardLayout.show(mainPanel, "RD");
            System.out.println("RD selected");
        });

        // Default page
        fdItem.setSelected(true);
        cardLayout.show(mainPanel, "FD");

        add(mainPanel);

        setVisible(true);
    }
}

class FDPanel extends JPanel {

    public FDPanel() {
        setBackground(new Color(210, 235, 255));

        JLabel label = new JLabel("FD Calculator Page");
        label.setFont(new Font("Calibri", Font.BOLD, 24));

        add(label);
    }
}

class RDPanel extends JPanel {

    public RDPanel() {
        setBackground(new Color(255, 240, 200));

        JLabel label = new JLabel("RD Calculator Page");
        label.setFont(new Font("Calibri", Font.BOLD, 24));

        add(label);
    }
}
```
