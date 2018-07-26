package com.ancrazyking.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * @author afeng
 * @date 2018/7/25 15:28
 * 用作显示的主窗体
 * 需要继承至Frame
 * <p>
 * <p>
 * 通过实现KeyListener接口,可以实现对触发事件的监听
 **/
public class MainFrame extends Frame implements KeyListener
{
    JLabel lab_greywolf;
    /**
     * 场景数据的模拟,使用二维数组模拟
     * 1代表障碍,0代表空地.
     * 4代表箱子
     */
    int[][] datas = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    /**
     * wolfX灰太狼横向的位置
     * wolfY灰太狼纵向的位置
     */
    int wolfX=1;
    int wolfY=1;

    public MainFrame()
    {
        /**应当按照由小到大的原则
         * 背景应当最后初始化
         * 初始化推箱子的灰太狼
         */
        initTarget();
        initGreyWolf();
        initLazySheep();
        initTree();
        /**
         * 初始化背景
         */
        initBackground();
        /**
         * 初始化窗口
         */
        setMainFrameUI();
        /**
         * 添加监听器
         */
        this.addKeyListener(this);
    }

    /**
     * 初始化游戏背景
     */
    private void initBackground()
    {
        //创建一个图片对象
        Icon icon = new ImageIcon("bg.png");
        //使用JLabel制作背景
        JLabel lab_bg = new JLabel(icon);
        //设置需要添加的组件的位置和大小
        lab_bg.setBounds(12, 36, 800, 600);
        this.add(lab_bg);
    }

    /**
     * 设置主窗体界面显示效果
     */
    private void setMainFrameUI()
    {
        //设置整个窗体的布局模式为自由布局
        this.setLayout(null);

        //设置窗口的标题
        this.setTitle("推箱子 v1.0 -懒羊羊与灰太狼");

        //设置窗口的位置
        this.setLocation(110, 30);

        //设置窗口尺寸
        this.setSize(826, 650);

        //设置窗口可见
        this.setVisible(true);

    }

    /**
     * 初始化推懒羊羊的灰太狼
     */
    private void initGreyWolf()
    {
        Icon icon = new ImageIcon("greyWolfDownMove.png");
        lab_greywolf = new JLabel(icon);
        lab_greywolf.setBounds(12 + wolfX * 50, 36 + wolfY * 50, 50, 50);
        this.add(lab_greywolf);
    }

    /**
     * 初始化懒羊羊
     * 搞了三张懒羊羊
     */
    private void initLazySheep()
    {
        Icon icon = new ImageIcon("lazySheep.png");
        JLabel lab_sheep1 = new JLabel(icon);
        lab_sheep1.setBounds(312, 236, 50, 50);
        this.add(lab_sheep1);
        datas[4][6] = 4;

        JLabel lab_sheep2 = new JLabel(icon);
        lab_sheep2.setBounds(312, 336, 50, 50);
        this.add(lab_sheep2);
        datas[6][6] = 4;

        JLabel lab_sheep3 = new JLabel(icon);
        lab_sheep3.setBounds(312, 436, 50, 50);
        this.add(lab_sheep3);
        datas[8][6] = 4;
    }

    /**
     * 障碍,树的初始化
     */
    private void initTree()
    {
        Icon icon = new ImageIcon("tree.png");
        for (int i = 0; i < datas.length; i++)
        {
            for (int j = 0; j < datas[i].length; j++)
            {
                /**
                 * 如果原始数据里面的值是1,则做障碍
                 */
                if (datas[i][j] == 1)
                {
                    JLabel label_tree = new JLabel(icon);
                    label_tree.setBounds(12 + 50 * j, 36 + 50 * i, 50, 50);
                    this.add(label_tree);
                }
            }
        }
    }

    /**
     * 笼子的初始化
     * 3个笼子
     */
    private void initTarget()
    {
        Icon icon = new ImageIcon("target.png");
        JLabel lab_target1 = new JLabel(icon);
        lab_target1.setBounds(712, 236, 50, 50);
        this.add(lab_target1);

        //制作其他两个笼子
        JLabel lab_target2 = new JLabel(icon);
        lab_target2.setBounds(712, 286, 50, 50);
        this.add(lab_target2);

        JLabel lab_target3 = new JLabel(icon);
        lab_target3.setBounds(712, 336, 50, 50);
        this.add(lab_target3);

    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    /**
     * 键盘监听处理的方法
     * key代表了你输入的是哪个按键
     * 左  37
     * 上  38
     * 右  39
     * 下  40
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        /**
         * 向右移动
         * 首先得知道原来人物的位置
         */
        if (key == 39)
        {

            /**
             * 碰撞检测之人物遇到障碍
             */
            if (datas[wolfY][wolfX + 1] == 1)
            {
                return;
            }
            /**
             * 碰撞检测之人物遇到箱子
             */
            if (datas[wolfY][wolfX + 1] == 4)
            {
                datas[wolfY][wolfX + 1] = 0;
                datas[wolfY][wolfX + 2] = 4;

                wolfX = wolfX + 1;
                int x = (int) lab_greywolf.getLocation().getX();
                int y = (int) lab_greywolf.getLocation().getY();
                lab_greywolf.setLocation(x + 50, y);
                Icon icon = new ImageIcon("greyWolfRightMove.png");
                lab_greywolf.setIcon(icon);
                return;
            }

            wolfX = wolfX + 1;
            int x = (int) lab_greywolf.getLocation().getX();
            int y = (int) lab_greywolf.getLocation().getY();
            lab_greywolf.setLocation(x + 50, y);
            Icon icon = new ImageIcon("greyWolfRightMove.png");
            lab_greywolf.setIcon(icon);
        }

        /**
         * 向左移动
         */
        if (key == 37)
        {

            if (datas[wolfY][wolfX-1] == 1)
            {
                return;
            }
            wolfX = wolfX - 1;
            int x = (int) lab_greywolf.getLocation().getX();
            int y = (int) lab_greywolf.getLocation().getY();
            lab_greywolf.setLocation(x - 50, y);
            Icon icon = new ImageIcon("greyWolfLeftMove.png");
            lab_greywolf.setIcon(icon);
        }
        /**
         * 向上移动
         */
        if (key == 38)
        {
            if (datas[wolfY-1][wolfX] == 1)
            {
                return;
            }
            wolfY = wolfY - 1;
            int x = (int) lab_greywolf.getLocation().getX();
            int y = (int) lab_greywolf.getLocation().getY();
            lab_greywolf.setLocation(x, y - 50);
            Icon icon = new ImageIcon("greyWolfTopMove.png");
            lab_greywolf.setIcon(icon);

        }
        /**
         * 向下移动
         */
        if (key == 40)
        {
            if (datas[wolfY + 1][wolfX] == 1)
            {
                return;
            }
            wolfY = wolfY + 1;
            int x = (int) lab_greywolf.getLocation().getX();
            int y = (int) lab_greywolf.getLocation().getY();
            lab_greywolf.setLocation(x, y + 50);
            Icon icon = new ImageIcon("greyWolfDownMove.png");
            lab_greywolf.setIcon(icon);
        }

    }

}

