package uk.ac.cam.sp794.oopjava.tick4star;
import uk.ac.cam.acr31.life.World;
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

public class GuiLife extends JFrame {
        private PatternPanel patternPanel;
        private ControlPanel controlPanel;
        private GamePanel gamePanel;
        private StatisticsPanel statisticsPanel;
        public GuiLife() {
                super("GuiLife");
                setSize(960,480);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setLayout(new BorderLayout());

                JComponent optionsPanel = createOptionsPanel();
                add(optionsPanel,BorderLayout.WEST);

                JComponent gamePanel = createGamePanel();
                add(gamePanel,BorderLayout.CENTER);

                JComponent statisticsPanel = createStatisticsPanel();
                add(statisticsPanel, BorderLayout.EAST);
        }
        private JComponent createStatisticsPanel(){
                statisticsPanel = new StatisticsPanel();
                addBorder(statisticsPanel,Strings.PANEL_STATISTICS);
                return statisticsPanel;

        }
        private JComponent createOptionsPanel() {
                Box result = Box.createVerticalBox();
                result.add(createSourcePanel());
                result.add(createPatternPanel());
                result.add(createControlPanel());
                return result;
        }
        private void addBorder(JComponent component, String title) {
                Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
                Border tb = BorderFactory.createTitledBorder(etch,title);
                component.setBorder(tb);
        }
        private JComponent createGamePanel() {
                JPanel holder = new JPanel();
                addBorder(holder,Strings.PANEL_GAMEVIEW);
                gamePanel = new GamePanel();
                holder.add(gamePanel);
                return new JScrollPane(holder);
        }
        private JComponent createSourcePanel() {
                JPanel result = new SourcePanel();
                addBorder(result,Strings.PANEL_SOURCE);
                return result;
        }
        private JComponent createPatternPanel() { 
                /*TODO*/ 
                patternPanel = new PatternPanel();
                addBorder(patternPanel,Strings.PANEL_PATTERN);
                return patternPanel;
        }
        private JComponent createControlPanel() { 
                /*TODO*/
                controlPanel = new ControlPanel();
                addBorder(controlPanel,Strings.PANEL_CONTROL);
                return controlPanel;
        }
        public static void main(String[] args) {
                GuiLife gui = new GuiLife();
                try {
                        String url="http://www.cl.cam.ac.uk/teaching/current/OOProg/life.txt";
                        List<Pattern> list = PatternLoader.loadFromURL(url);
                        gui.patternPanel.setPatterns(list);
                        CommandLineOptions c = new CommandLineOptions(args);
                        int index = 5;
                        if(c.getIndex() != null)
                                index = c.getIndex();
                        World w = gui.controlPanel.initialiseWorld(list.get(index));
                        gui.statisticsPanel.update(w);
                        gui.gamePanel.display(w);
                } catch (IOException ioe) {
                        //TODO: don't leave empty exception handlers!
                        ioe.printStackTrace();
                } catch (PatternFormatException e){
                        System.out.println(e.getMessage());
                } catch (CommandLineException e){
                        System.out.println(e.getMessage());
                        return;
                }
                gui.setVisible(true);
        }
}
