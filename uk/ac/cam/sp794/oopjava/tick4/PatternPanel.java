package uk.ac.cam.sp794.oopjava.tick4;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.util.List;
import java.util.ArrayList;
import java.awt.BorderLayout;
public class PatternPanel extends JPanel{
        private JList<Object> guiList;

        public PatternPanel(){
                super();
                setLayout(new BorderLayout());
                guiList = new JList<Object>();
                add(new JScrollPane(guiList));
        }
        public void setPatterns(List<Pattern> list){
                ArrayList<String> names = new ArrayList<String>();
                for(Pattern p:list){
                        names.add(p.getName()+" ("+p.getAuthor()+")");
                }
                guiList.setListData(names.toArray());
        }
}
