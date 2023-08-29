import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener, MouseListener, KeyListener {
    public static final int BOARD_WIDTH = 900, BOARD_HEIGHT = 600;
    private final int BLOCK_SIZE = 60;
    private final int N_BLOCKS = 15;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private int dayCounter, mouseX, mouseY;
    private Image grassOne, grassTwo, dirtOne, cornerTrees, sideTrees;
    private Image mine, hub, wallPiece, spawnery;
    private boolean isInBuildMode, enableSpawnery;
    private Timer timer;
    private Mine mineBuilding;
    private Hub hubBuilding;
    private Spawnery spawneryBuilding;
    private ArrayList<Goblin> goblins = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();
    private Inventory inventory;
    private JLabel resourceLabel, statusLabel;

    public Board() {
        loadUI();
        loadGraphics();
        loadValues();
        loadCreatures();
        loadBuildings();
    }

    private void loadUI() {
        setFocusable(true);
        addMouseListener(this);
        addKeyListener(this);
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        inventory = new Inventory();
        resourceLabel = new JLabel();
        resourceLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        resourceLabel.setText("Stone: " + inventory.getTotalRocks() + " Wood: " + inventory.getTotalWood());
        resourceLabel.setForeground(Color.WHITE);
        statusLabel = new JLabel();
        statusLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        statusLabel.setForeground(Color.WHITE);
        add(statusLabel);
        add(resourceLabel);
    }

    private void loadGraphics() {
        grassOne = new ImageIcon("images/terrain/grass1.png").getImage();
        dirtOne = new ImageIcon("images/terrain/dirt.jpg").getImage();
        grassTwo = new ImageIcon("images/terrain/grass1.png").getImage();
        mine = new ImageIcon("images/buildings/mine.png").getImage();
        hub = new ImageIcon("images/buildings/hub.png").getImage();
        spawnery = new ImageIcon("images/buildings/cauldron1.png").getImage();
        cornerTrees = new ImageIcon("images/terrain/corner-trees.png").getImage();
        sideTrees = new ImageIcon("images/terrain/side-trees.png").getImage();
        wallPiece = new ImageIcon("images/buildings/wall-piece.png").getImage();
    }

    private void loadValues() {
        isInBuildMode = false;
        enableSpawnery = false;
        dayCounter = 0;
        mouseX = 0;
        mouseY = 0;
        timer = new Timer(50, this);
        timer.start();
    }

    private void loadBuildings() {
        mineBuilding = new Mine(mine, 170, 120);
        hubBuilding = new Hub(hub, 500, 300);
    }

    private void loadCreatures() {
        Goblin minerGoblinOne = new Goblin(10, "images/creatures/goblin2.png", 300, 300, Goblin.MINER);
        Goblin minerGoblinTwo = new Goblin(10, "images/creatures/goblin2.png", 450, 450, Goblin.MINER);
        Goblin lumberGoblinOne = new Goblin(10, "images/creatures/goblin2.png", 550, 450, Goblin.LUMBERER);
        goblins.add(minerGoblinOne);
        goblins.add(minerGoblinTwo);
        goblins.add(lumberGoblinOne);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawWorld(g2d);
        drawCreatures(g2d);
    }

    private void drawWorld(Graphics2D g2d) {
        short i = 0;
        int x, y;

        try {
            // paints the level based on number in array
            for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
                if (i == TerrainData.WORLD_DATA.length)
                    break;
                for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {
                    if (TerrainData.WORLD_DATA[i] == 0) {
                        g2d.drawImage(grassOne, x, y, this);
                    } else if (TerrainData.WORLD_DATA[i] == 1) {
                        g2d.drawImage(dirtOne, x, y, this);
                    } else if (TerrainData.WORLD_DATA[i] == 3) {
                        g2d.drawImage(grassTwo, x, y, this);
                    }
                    i++;

                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.fillInStackTrace());
        }

        g2d.drawImage(mineBuilding.getImage(), mineBuilding.getX(), mineBuilding.getY(), this);
        g2d.drawImage(cornerTrees, 0, 0, this);
        g2d.drawImage(sideTrees, 730, 50, this);
        g2d.drawImage(hubBuilding.getImage(), hubBuilding.getX(), hubBuilding.getY(), this);

        for (Wall wall : walls)
            if (wall.isVisible())
                g2d.drawImage(wall.getImage(), wall.getX(), wall.getY(), this);

        if (Crafting.spawneryWasCrafted)
            g2d.drawImage(spawneryBuilding.getImage(), spawneryBuilding.getX(), spawneryBuilding.getY(), this);

    }

    private void drawCreatures(Graphics2D g2d) {
        for (Goblin goblin : goblins)
            g2d.drawImage(goblin.getCreatureImage(), goblin.getX(), goblin.getY(), this);
    }

    public void actionPerformed(ActionEvent e) {
        dayCounter += 1;

        if (dayCounter % 50 == 0) {
            resourceLabel.setText("Stone: " + inventory.getTotalRocks() + " Wood: " + inventory.getTotalWood() + " ");
            if (Crafting.wallWasCrafted) {
                walls.get(walls.size() - 1).setVisible(true);
                Crafting.wallWasCrafted = false;
            }
        }

        if (dayCounter > 1000)
            dayCounter = 0;

        for (Goblin goblin : goblins)
            goblin.goblinBehavior();

        repaint();
    }

    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (isInBuildMode) {
            TerrainData.setBuildingX(mouseX);
            TerrainData.setBuildingY(mouseY);
            statusLabel.setText("");
            if (Crafting.getBuilding() == Crafting.WALL) {
                goblins.get(0).setCurrentRole(Goblin.CRAFTER);
                goblins.get(0).setCraftingBuilding(true);
                walls.add(new Wall(wallPiece, mouseX, mouseY));
                walls.get(walls.size() - 1).setVisible(false);
                isInBuildMode = false;
            } else if (Crafting.getBuilding() == Crafting.SPAWNERY) {
                goblins.get(0).setCurrentRole(Goblin.CRAFTER);
                goblins.get(0).setCraftingBuilding(true);
                spawneryBuilding = new Spawnery(spawnery, mouseX, mouseY);
                isInBuildMode = false;

            }
        }
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
        int keyNumber = Character.getNumericValue(keyChar);
        if (keyChar == 'w' && !isInBuildMode) {
            System.out.println("Build mode for wall is on");
            statusLabel.setText("Click to build a wall     ");
            Crafting.setBuilding(Crafting.WALL);
            isInBuildMode = true;
        } else if (keyChar == 's' && !Crafting.spawneryWasCrafted && !isInBuildMode) {
            System.out.println("Build mode for spawnery is on");
            statusLabel.setText("Click to build a spawnery     ");
            Crafting.setBuilding(Crafting.SPAWNERY);
            isInBuildMode = true;
        } else if (keyChar == 's') {
            statusLabel.setText("Spawn Guard 50s 50w(g)    ");
            enableSpawnery = true;
        } else if (enableSpawnery && keyChar == 'g') {
            goblins.add(new Goblin(10, "images/creatures/goblin2.png", Crafting.spawneryX + 50, Crafting.spawneryY + 60, Goblin.GUARD));
        }
    }

    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}
}
