package uk.ac.cam.sp794.oopjava.tick5star;

import uk.ac.cam.acr31.life.World;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class Editor extends JFrame {
        private PatternPanel patternPanel;
        private GamePanel gamePanel;
        private GenerationPanel generationPanel;
        private World world;
        private String name,author;
        public Editor() {

                super("Editor");
                setSize(640,560);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setLayout(new BorderLayout());

                try{
                        JComponent patternPanel = createPatternPanel();
                        add(patternPanel, BorderLayout.WEST);
                        JComponent gamePanel = createGamePanel();
                        add(gamePanel,BorderLayout.CENTER);

                        try{
                                this.gamePanel.sync(this.patternPanel.getCurrentPattern());
                        }catch (PatternFormatException e){
                                JOptionPane.showMessageDialog(this, "Error initialising world","An error occurred when initialising the world. "+e.getMessage(),
                                                JOptionPane.ERROR_MESSAGE);
                        }

                        JComponent generationPanel = createGenerationPanel(this.gamePanel.getCurrentWorld());
                        add(generationPanel, BorderLayout.SOUTH);
                        repaint();
                } catch (PatternFormatException e){
                        e.printStackTrace();
                }
        }

        private void addBorder(JComponent component, String title) {
                Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
                Border tb = BorderFactory.createTitledBorder(etch,title);
                component.setBorder(tb);
        }
        private JComponent createGenerationPanel(World w) {
                JPanel holder = new JPanel();
                addBorder(holder, "Future Generations"); //method as defined in tick5.Editor
                generationPanel = new GenerationPanel(w){
                        protected void step(World w){
                                patternPanel.sync(w);
                                try{
                                        gamePanel.sync(patternPanel.getCurrentPattern());
                                }catch(PatternFormatException e){}
                                generationPanel.sync(w);

                        }
                };   //TODO: define field for filmStripPanel
                holder.add(generationPanel);
                JScrollPane sp = new JScrollPane(holder);
                sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                return sp;
        }
        private JComponent createGamePanel() {
                JPanel holder = new JPanel();
                addBorder(holder,Strings.BOARD_GAME);
                gamePanel = new GamePanel(){
                        protected void onWorldChanged(){
                                patternPanel.sync(gamePanel.getCurrentWorld());
                                generationPanel.sync(gamePanel.getCurrentWorld());
                        }
                };

                holder.add(gamePanel);
                return new JScrollPane(holder);
        }
        private JComponent createPatternPanel() throws PatternFormatException{ 
                /*TODO*/ 
                patternPanel = new PatternPanel(){
                        protected void onWorldChanged(){
                                try{
                                        gamePanel.sync(patternPanel.getCurrentPattern());
                                        generationPanel.sync(gamePanel.getCurrentWorld());
                                } catch (PatternFormatException e){}

                        }
                };
                addBorder(patternPanel,Strings.BOARD_PATTERN);
                return patternPanel;
        }


        public static void main(String[] args) {
                Editor gui = new Editor();
                gui.setVisible(true);
        }
}
