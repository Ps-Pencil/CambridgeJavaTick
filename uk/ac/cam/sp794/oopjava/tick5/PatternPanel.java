package uk.ac.cam.sp794.oopjava.tick5;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.util.List;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public abstract class PatternPanel extends JPanel{
        private JList<Object> guiList;
        private List<Pattern> patternList;
        private Pattern currentPattern;

        protected abstract void onPatternChange();
        public PatternPanel(){
                super();
                currentPattern = null;
                setLayout(new BorderLayout());
                guiList = new JList<Object>();
                
                guiList.addListSelectionListener(new ListSelectionListener() {
                        public void valueChanged(ListSelectionEvent e) {
                                if (!e.getValueIsAdjusting() && (patternList != null)) {
                                        int sel = guiList.getSelectedIndex();
                                        if (sel != -1) {
                                                currentPattern = patternList.get(sel);
                                                onPatternChange();
                                        }
                                }
                        }
                });
                add(new JScrollPane(guiList));
        }
        public Pattern getCurrentPattern(){
                return currentPattern;
        }
        public void setPatterns(List<Pattern> list){
                patternList = list;
                if(list == null || list.size() == 0){
                        currentPattern = null;
                        guiList.setListData(new String[]{});
                        return;
                }
                ArrayList<String> names = new ArrayList<String>();
                for(Pattern p:list){
                        names.add(p.getName()+" ("+p.getAuthor()+")");
                }
                guiList.setListData(names.toArray());
                currentPattern = list.get(0);
                guiList.setSelectedIndex(0);
        }
}
