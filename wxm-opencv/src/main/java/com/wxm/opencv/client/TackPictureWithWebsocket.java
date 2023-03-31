package com.wxm.opencv.client;

import com.alibaba.fastjson.JSON;
import com.wxm.opencv.demo.demo.Demo5;
import com.wxm.opencv.websocket.server.WebsocketServer;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Properties;
import java.util.UUID;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-03-21 16:53:19
 */
@Slf4j
public class TackPictureWithWebsocket {
    /**
     * Windows 系统 默认路径
     */
    private static final String DEFAULT_PATH_WIN = "C:\\temp\\img";
    /**
     * 其他系统默认路径
     */
    private static final String DEFAULT_PATH_OTHER = "/usr/local";
    /**
     * 图片列表中图片显示大小
     */
    private static final int IMG_SIZE = 150;
    /**
     * 最大拍照数
     */
    private static final int MAX_ROWS = 100;
    private int mouseAtX = 0;
    private int mouseAtY = 0;

    private static final String IMG_SUFFIX = "jpg";

    /**
     * 摄像头图片区域
     */
    private final JPanel panelCamera = new JPanel();
    /**
     * 图片列表区域
     */
    private final JScrollPane panelImgList = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    /**
     * 按钮等功能区域
     */
    private final JPanel panelFn = new JPanel();


    private final JLabel labelImg = new JLabel();


    private final JLabel labelPath = new JLabel("路径");
    private final JTextField fieldPath = new JTextField("");
    private final JButton btnTakePicture = new JButton("拍照");
    private final JButton btnRotate = new JButton("旋转");
    private final JButton btnScaleIn = new JButton("放大");
    private final JButton btnScaleOut = new JButton("缩小");
    private final JButton btnQuit = new JButton("确定");
    private final JButton btnCancel = new JButton("取消");

    private static double cameraWidth = 0;
    private static double cameraHeight = 0;

    /**
     * 旋转角度
     */
    private int angle = 0;
    /**
     * 放缩比例
     */
    private double scale = 1.0;

    private BufferedImage bufferedImage = null;
    private ImageIcon icon = new ImageIcon("");
    private final JPanel imgListPanel = new JPanel(new GridLayout(MAX_ROWS, 1));

    private java.util.List<String> list = new ArrayList<>();

    private VideoCapture camera = null;

    private String uid;

    private void initBox() {
        int wh = (int) (cameraHeight > cameraWidth ? cameraHeight : cameraWidth);
        int gap = 5;
        int width = wh;
        int height = wh;
        panelCamera.setSize(width, height);
        panelCamera.setLocation(gap, gap);
        panelCamera.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        panelCamera.setBackground(Color.WHITE);

        int widthImgList = 175;
        int heightImgList = height;
        panelImgList.setBounds(width + 2 * gap, gap, widthImgList, heightImgList);
        panelImgList.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        panelImgList.setBackground(Color.WHITE);

        int widthFn = width + widthImgList + 4 * gap;
        int heightFn = 70;
        panelFn.setSize(widthFn, heightFn);
        panelFn.setLocation(gap, heightImgList + 10);
        panelFn.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        panelFn.setBackground(Color.WHITE);
    }

    private void btnFn(JFrame frame) {
        btnTakePicture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // do something
                System.out.println("点击了拍照按钮，现在开始拍照！");

                try {
                    String dir = fieldPath.getText();
                    String uuid = UUID.randomUUID().toString();
                    String filePath = dir + File.separator + uid + File.separator + uuid + "." + IMG_SUFFIX;

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImage, IMG_SUFFIX, baos);

                    String fileBase64 = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(baos.toByteArray()).replaceAll("\\s*|\r|\n|\t", "");
                    // jdk1.9之后，java.sun.misc.BASE64Encoder 被取消
//                    String fileBase64 = "data:image/jpeg;base64," + new BASE64Encoder().encode(baos.toByteArray()).replaceAll("\\s*|\r|\n|\t", "");
                    log.info(">>>>fileBase64:{}", fileBase64);

                    // 1. 拍照图片保存到本地
                    File file = new File(filePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    ImageIO.write(bufferedImage, IMG_SUFFIX, file);
                    // 2. 右侧列表显示拍照结果
                    imgListPanel.setBounds(0, 0, IMG_SIZE, Integer.MAX_VALUE);
                    icon.setImage(icon.getImage().getScaledInstance(IMG_SIZE, IMG_SIZE, Image.SCALE_DEFAULT));
                    JLabel jlabel = new JLabel(icon);
                    imgListPanel.setLayout(new GridLayout(imgListPanel.getComponentCount() + 1, 1));
                    imgListPanel.add(jlabel);
                    panelImgList.setViewportView(imgListPanel);
                    // 滚动条最下面
                    JScrollBar sbar = panelImgList.getVerticalScrollBar();
                    sbar.setValue(sbar.getMaximum());

                    // 3. 图片结果放在list中
                    Base64File fileBean = new Base64File();
                    fileBean.setGuid(uuid).setBase64File(fileBase64).setUrl(filePath).setSuffix(IMG_SUFFIX).setUid(uid);
                    list.add(fileBase64);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnRotate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // do something
                System.out.println("点击了旋转按钮，现在开始旋转！");
                angle += -90; // 顺时针旋转90度

            }
        });
        btnScaleIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // do something
                System.out.println("点击了放大按钮，现在开始放大！");
                if (scale < 10.0) {
                    scale += 0.2;
                }
            }
        });
        btnScaleOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // do something
                System.out.println("点击了缩小按钮，现在开始缩小！");
                if (scale >= 0.4) {
                    scale -= 0.2;
                }
            }
        });
        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // do something
                System.out.println("点击了确定按钮，现在开始退出！");
                // 1. 返回 图片list
                System.out.println("图片：" + JSON.toJSONString(list));


                WebsocketServer.sendMessage(JSON.toJSONString(list),uid);

                camera.release();
                // 2. 退出
                frame.dispose();
                // 采用websocket 关闭窗口和摄像头，而不是退出程序
                // System.exit(0);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击了取消按钮，现在开始退出！");
                camera.release();
                frame.dispose();
                // 采用websocket 关闭窗口和摄像头，而不是退出程序
                // System.exit(0);
            }
        });

    }

    private void initFnBox() {
        String defaultValue = System.getProperty("os.name").toLowerCase().contains("win") ? DEFAULT_PATH_WIN : DEFAULT_PATH_OTHER;
        fieldPath.setText(defaultValue);
        panelFn.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelFn.add(labelPath);
        panelFn.add(fieldPath);
        JPanel btnPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        btnPanel.add(btnTakePicture);
        btnPanel.add(btnRotate);
        btnPanel.add(btnScaleIn);
        btnPanel.add(btnScaleOut);
        btnPanel.add(btnQuit);
        btnPanel.add(btnCancel);
        panelFn.add(btnPanel);
    }

    private void initJFrame(JFrame frame) {
        Container container = frame.getContentPane();

        container.add(panelCamera);
        container.add(panelImgList);
        container.add(panelFn);

        frame.setUndecorated(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(100, 80, panelFn.getWidth() + 10, panelCamera.getHeight() + panelFn.getHeight() + 15);
        frame.setResizable(false);
    }

    public TackPictureWithWebsocket(String id) throws Exception {
        uid = id;
        /**
         * 数 “摄像头ID号”：摄像设备（摄像头）的 ID 编号，默认值为 -1，表示随机选取一个摄像头。
         *
         * 如果有多个摄像头，则用数字 “0”， “1” 等表示
         * 如果只有一个摄像头，既可以使用“0”也可以使用“-1” 作为摄像头ID号
         */
        camera = new VideoCapture(0);
        // 第一次没打开，打开
        if (!camera.isOpened()) {
            camera.open(0);
        }
        // 在此检测，没打卡，报错
        if (!camera.isOpened()) {
            throw new RuntimeException("摄像头初始化失败");

        }
        cameraHeight = camera.get(Videoio.CAP_PROP_FRAME_HEIGHT);
        cameraWidth = camera.get(Videoio.CAP_PROP_FRAME_WIDTH);
        if (cameraHeight == 0 || cameraWidth == 0) {
            throw new RuntimeException("camera not found!");
        }


        JFrame frame = new JFrame("拍照");
        initBox();


        /**
         * 默认图片，必须，否则摄像头不能加载
         */
        ImageIcon icon0 = new ImageIcon("");
        labelImg.setIcon(icon0);
        panelCamera.add(labelImg);


        initFnBox();


        btnFn(frame);

        initJFrame(frame);


        Mat mat = new Mat();
        while (camera.read(mat)) {

            Mat dst = mat.clone();
            //定义图像的中心
            org.opencv.core.Point center = new Point(mat.width() / 2, mat.height() / 2);
            //旋转的角度：33.0，缩放的比例：1.0
            Mat rotationMatrix2D = Imgproc.getRotationMatrix2D(center, angle, scale);
            Imgproc.warpAffine(mat, dst, rotationMatrix2D, dst.size(), Imgproc.INTER_NEAREST);

            bufferedImage = mat2BI(dst);
            icon = new ImageIcon(bufferedImage);
            labelImg.setIcon(icon);
            panelCamera.setLayout(new BorderLayout());
            panelCamera.add(labelImg, BorderLayout.CENTER);

            mat.release();
        }
    }

    private BufferedImage mat2BI(Mat mat) {
        int dataSize = mat.cols() * mat.rows() * (int) mat.elemSize();
        byte[] data = new byte[dataSize];
        mat.get(0, 0, data);
        int type = mat.channels() == 1 ?
                BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_3BYTE_BGR;

        if (type == BufferedImage.TYPE_3BYTE_BGR) {
            for (int i = 0; i < dataSize; i += 3) {
                byte blue = data[i + 0];
                data[i + 0] = data[i + 2];
                data[i + 2] = blue;
            }
        }
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        image.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);

        return image;
    }

    static {
//        URL url = ClassLoader.getSystemResource("third-pard/opencv/opencv_java460.dll");
//        System.load(url.getPath());
//        String systemName = System.getProperty("os.name");
//        System.out.println("system name is :"+systemName);
        // 从配置文件中读取 opencv.properties

        String userDir = System.getProperty("user.dir");
        // 开发环境（是，模块所在的目录），比如wxm-optencv 所在目录是wxm-datax
        System.out.println(">>>>>>>userDir>>>" + userDir);
        // 读取jar包同级目录下 config文件夹下的配置文件
        String filePath = userDir + File.separator + "config" + File.separator + "opencv.properties";
        try {
            InputStream in = new FileInputStream(new File(filePath));
            Properties properties = new Properties();
            properties.load(in);
            String opencvDll = properties.getProperty("opencv.java.dll");
            System.load(opencvDll);
        } catch (FileNotFoundException e) {
            log.info("user.dir>>>>>>>{}", e.getMessage());
            //读取配置文件，jar包同级的配置文件
            Properties properties = new Properties();
            File file = new File("opencv.properties");
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                properties.load(fis);
                String opencvDll = properties.getProperty("opencv.java.dll");
                System.load(opencvDll);
                fis.close();
            } catch (FileNotFoundException ex) {
                log.info("jar properties>>>>>>>{}", e.getMessage());
                // jar包中的配置文件
                InputStream in = Demo5.class.getClassLoader().getResourceAsStream("opencv.properties");
                try {
                    properties.load(in);
                } catch (IOException exc) {
                    throw new RuntimeException(exc);
                }
                String opencvDll = properties.getProperty("opencv.java.dll");
                System.load(opencvDll);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) throws Exception {

        new TackPictureWithWebsocket("wxm");
    }


    @Data
    @Accessors(chain = true)
    class Base64File {
        // 调用者的唯一Id
        private String uid;
        private String guid;
        private String base64File;

        private String url;
        private String suffix;
    }
}
