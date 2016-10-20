package uk.ac.cam.sp794.oopjava.tick5star;
import uk.ac.cam.acr31.life.World;
import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
public abstract class PatternPanel extends JPanel{
        private Pattern pattern;
        private JTextField patternString,name,author,rows;
        private JSpinner width,height,startx,starty;

        protected abstract void onWorldChanged();

        public Dimension getPreferredSize() {
                return new Dimension(220, 460);
        }
        public Pattern getCurrentPattern(){
                return pattern;
        
        }
        public void sync(World w){
                if(w == null) return;
                int minX = 10000,maxX = -1;
                int minY = 10000, maxY = -1;
                for(int y = 0;y<w.getHeight();y++){
                        for(int x=0;x<w.getWidth();x++){
                                if(w.getCell(x,y)){
                                        minX = Math.min(x,minX);
                                        minY = Math.min(y,minY);
                                        maxX = Math.max(x,maxX);
                                        maxY = Math.max(y,maxY);
                                }
                        }
                }
                String rows = "";
                for(int y = minY;y<=maxY;y++){
                        for(int x=minX;x<=maxX;x++){
                                if(w.getCell(x,y))
                                        rows+="1";
                                else
                                        rows+="0";
                        }
                        rows+=" ";
                }
                if(minY==10000){
                        minX=0;
                        maxX=0;
                        minY=0;
                        maxY=0;
                }
                rows = rows.trim();
                pattern.setWidth(w.getWidth());
                pattern.setHeight(w.getHeight());
                pattern.setStartCol(minX);
                pattern.setStartRow(minY);
                try{
                        pattern.setRows(rows);
                }catch(PatternFormatException e){}

                patternString.setText(pattern.getTotal());
                name.setText(pattern.getName());
                author.setText(pattern.getAuthor());
                width.setValue(pattern.getWidth());
                height.setValue(pattern.getHeight());
                startx.setValue(pattern.getStartCol());
                starty.setValue(pattern.getStartRow());
                this.rows.setText(pattern.getRows());
        }


        private JTextField addTextField(String title, String placeholder){

                add(new JLabel(title,javax.swing.SwingConstants.CENTER));
                JTextField res = new JTextField(placeholder);
                add(res);
                return res;
        }
        private JSpinner addSpinner(String title, int initial, int min, int max, int step){

                add(new JLabel(title,javax.swing.SwingConstants.CENTER));
                JSpinner res = new JSpinner(new SpinnerNumberModel(initial,min,max,step));
                add(res);
                return res;
        }
        public PatternPanel() throws PatternFormatException{
                super();
                setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
                patternString = addTextField(Strings.FIELD_PATTERN,Strings.EMPTY_PATTERN);
                pattern = new Pattern(patternString.getText());
                name = addTextField(Strings.FIELD_NAME,"");
                author = addTextField(Strings.FIELD_AUTHOR,"");
                width = addSpinner(Strings.FIELD_WIDTH,8,8,1024,1);
                height = addSpinner(Strings.FIELD_HEIGHT,8,8,1024,1);
                startx = addSpinner(Strings.FIELD_STARTX,0,0,1024,1);
                starty = addSpinner(Strings.FIELD_STARTY,0,0,1024,1);
                rows = addTextField(Strings.FIELD_ROWS,"0");

                patternString.addFocusListener(new FocusListener(){
                        public void focusGained(FocusEvent e){}
                        public void focusLost(FocusEvent e){
                                String old = pattern.getTotal();
                                try{
                                        pattern = new Pattern(patternString.getText());
                                }catch(PatternFormatException pfe){
                                        patternString.setText(old);
                                        return;
                                }
                                patternString.setText(pattern.getTotal());
                                name.setText(pattern.getName());
                                author.setText(pattern.getAuthor());
                                width.setValue(pattern.getWidth());
                                height.setValue(pattern.getHeight());
                                startx.setValue(pattern.getStartCol());
                                starty.setValue(pattern.getStartRow());
                                rows.setText(pattern.getRows());
                                
                                onWorldChanged();
                        }
                });
                name.addFocusListener(new FocusListener(){
                        public void focusGained(FocusEvent e){}
                        public void focusLost(FocusEvent e){
                                pattern.setName(name.getText());
                                patternString.setText(pattern.getTotal());
                        }
                });
                author.addFocusListener(new FocusListener(){
                        public void focusGained(FocusEvent e){}
                        public void focusLost(FocusEvent e){
                                pattern.setAuthor(author.getText());
                                patternString.setText(pattern.getTotal());
                        }
                });
                rows.addFocusListener(new FocusListener(){
                        public void focusGained(FocusEvent e){}
                        public void focusLost(FocusEvent e){
                                String old = pattern.getRows();
                                try{
                                        pattern.setRows(rows.getText());
                                }catch(PatternFormatException pe){
                                        rows.setText(old);
                                        try{
                                                pattern.setRows(old);
                                        }catch (PatternFormatException ppe){}
                                        return;
                                }
                                patternString.setText(pattern.getTotal());
                                onWorldChanged();
                        }
                });
                ((JSpinner.DefaultEditor) width.getEditor()).getTextField().addFocusListener(new FocusListener(){
                        public void focusGained(FocusEvent e){}
                        public void focusLost(FocusEvent e){
                                try{
                                        width.commitEdit();
                                }catch(ParseException pe){
                                        width.setValue(8);
                                }
                                pattern.setWidth((Integer)width.getValue());
                                patternString.setText(pattern.getTotal());
                                onWorldChanged();
                        }
                });
                ((JSpinner.DefaultEditor) height.getEditor()).getTextField().addFocusListener(new FocusListener(){
                        public void focusGained(FocusEvent e){}
                        public void focusLost(FocusEvent e){
                                try{
                                        height.commitEdit();
                                } catch (ParseException pe){
                                        height.setValue(8);
                                }
                                pattern.setHeight((Integer)height.getValue());
                                patternString.setText(pattern.getTotal());
                                onWorldChanged();
                        }
                });
                ((JSpinner.DefaultEditor) startx.getEditor()).getTextField().addFocusListener(new FocusListener(){
                        public void focusGained(FocusEvent e){}
                        public void focusLost(FocusEvent e){
                                try{
                                        startx.commitEdit();
                                } catch (ParseException pe){
                                        startx.setValue(0);
                                }
                                pattern.setStartCol((Integer)startx.getValue());
                                patternString.setText(pattern.getTotal());
                                onWorldChanged();
                        }
                });
                ((JSpinner.DefaultEditor) starty.getEditor()).getTextField().addFocusListener(new FocusListener(){
                        public void focusGained(FocusEvent e){}
                        public void focusLost(FocusEvent e){
                                try{
                                        starty.commitEdit();
                                } catch (ParseException pe){
                                        starty.setValue(0);
                                }
                                pattern.setStartRow((Integer)starty.getValue());
                                patternString.setText(pattern.getTotal());
                                onWorldChanged();
                        }
                });

                

        }

}
