import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MainFrame extends JFrame {
    private LoginFrame login = new LoginFrame();
    private int screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frmW = 800, frmH = 750;
    private JMenuBar mbr = new JMenuBar();
    private JMenu mFile = new JMenu("File");
    private JMenu mSet = new JMenu("Set");
    private JMenu mGame = new JMenu("Game");
    private JMenu mAbout = new JMenu("About");
    private JMenuItem miFileExit = new JMenuItem("Exit");
    private JMenuItem miGameLotto = new JMenuItem("Lotto");
    private Random rnd = new Random(System.currentTimeMillis());

    private JDesktopPane jdp = new JDesktopPane();
    private JInternalFrame infLotto = new JInternalFrame("Lotto");
    private JPanel panUP = new JPanel(new GridLayout(1,6,3,3));
    private JPanel panDOWN = new JPanel(new GridLayout(1,2,3,3));
    private JLabel labs [] = new JLabel [6];
    private int labList[] = new int [6];
    private JButton btnClose = new JButton("Close");
    private JButton btnRe = new JButton("Re-Generated");

    public MainFrame(LoginFrame loginframe){
        login = loginframe;
        initComp();
    }
    private void initComp(){
        this.setBounds(screenW/2 - frmW/2, screenH/2 - frmH/2, frmW, frmH);
        this.setJMenuBar(mbr);
        this.setContentPane(jdp);
        infLotto.setBounds(0,0,400,150);
        mbr.add(mFile);
        mbr.add(mSet);
        mbr.add(mGame);
        mbr.add(mAbout);
        mFile.add(miFileExit);
        mGame.add(miGameLotto);
        jdp.add(infLotto);
        infLotto.setLayout(new BorderLayout(3,3));
        panDOWN.add(btnClose);
        panDOWN.add(btnRe);
        infLotto.add(panUP, BorderLayout.CENTER);
        infLotto.add(panDOWN, BorderLayout.SOUTH);
        regenerate();

        miFileExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        miFileExit.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        miGameLotto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infLotto.setVisible(true);
            }
        });
        miGameLotto.setAccelerator(KeyStroke.getKeyStroke('G',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infLotto.setVisible(false);
            }
        });

        btnRe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regenerate();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                login.setVisible(true);
                dispose();
            }
        });
    }

    private void regenerate(){
        boolean check ;
        for(int i = 0; i < 6;i++){
            check = true;
            while(check){
                check = false;
                int tmp = rnd.nextInt(49) + 1;
                for(int j = 0; j < i; j++){
                    if(labList[j] == tmp){
                        check = true;
                    }
                }
                labList[i] = tmp;
            }
            labs[i] = new JLabel();
            panUP.add(labs[i]);
            labs[i].setText(Integer.toString(labList[i]));
        }

    }
}
