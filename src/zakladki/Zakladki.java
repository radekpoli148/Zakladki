package zakladki;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

public class Zakladki extends JFrame{

    public Zakladki()
    {
        initComponents();
    }
    public void initComponents()
    {
        this.setTitle("Zakladki");
        this.setBounds(300, 320, 400, 200);
        
        zakladki.addTab("tab1", new JLabel("to jest pierwszy"));
        zakladki.setMnemonicAt(0, KeyEvent.VK_T);
        
        JPanel panel = new JPanel();
        panel.add(new JButton("test"));
        
        zakladki.addTab("tab2", new ImageIcon("zielony.png"), panel, "oto druga zakladka");
        
        zakladki.addTab("Video Kurs", panelTworzenia);
        panelTworzenia.add(new JLabel("Stwórz nowy plik o nazwie: "));
        JTextField nazwaNowejZakladki = new JTextField(7);
        panelTworzenia.add(nazwaNowejZakladki);
        JButton stwoz = new JButton("Stwoz");
        panelTworzenia.add(stwoz);
        
        stwoz.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JPanel panelTekstowy = new JPanel();
                panelTekstowy.setLayout(new GridLayout(1, 1));
                JTextArea obszarTekstowy = new JTextArea();
                panelTekstowy.add(new JScrollPane(obszarTekstowy));
                
                zakladki.addTab(nazwaNowejZakladki.getText()+".txt", panelTekstowy);
                zakladki.setSelectedIndex(zakladki.getTabCount()-1);
                
                obszarTekstowy.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(!(tytulZakladki+e.getKeyChar()).equals(przedZmianaObaszaruTekstowego) && czyToAscii(e.getKeyChar()) && flagaZapisu == true)
                        {
                            przedZmianaObaszaruTekstowego = tytulZakladki+e.getKeyChar();
                            zakladki.setTitleAt(indeksZakladki, tytulZakladki+"*");
                            flagaZapisu = false;
                            System.out.println("Test");
                        }
                        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S && flagaZapisu == false)
                        {
                            zakladki.setTitleAt(indeksZakladki, tytulZakladki);
                            System.out.println("Zapisujemy");
                            flagaZapisu = true;
                        }
                    }
                    private boolean czyToAscii(char zn)
                    {
                        for(int i = 0; i < 256; i++)
                            if(zn == i)
                                return true;

                        return false;
                    }
                    String przedZmianaObaszaruTekstowego = "";
                    int indeksZakladki = zakladki.getSelectedIndex();
                    String tytulZakladki = zakladki.getTitleAt(indeksZakladki);
                    boolean flagaZapisu = true;
                
                });
            }
        });
        zakladki.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        this.getContentPane().add(zakladki);
        this.setDefaultCloseOperation(3);
    }
    
    private JTabbedPane zakladki = new JTabbedPane();
    private JPanel panelTworzenia = new JPanel();
    
    public static void main(String[] args) {
        new Zakladki().setVisible(true);
    }
    
}
