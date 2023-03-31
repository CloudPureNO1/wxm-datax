# Java加载dll或so库文件的路径 java.library.path
 
1. System.load(绝对路径)
> System.load("D:\\ProgramFiles\\opencv\\opencv\\build\\java\\x64\\opencv_java460.dll")
2. System.loadLibrary(相对路径)
3. 在linux下添加一个java.library.path的方法如下：
   > 在/etc/profile 后面加上一行 export LB_LIBRARY_PATH=路径
5. 在执行程序的时候可以显示指定， -Djava.library.path=路径，这种会清除掉预设置的java.library.path的值 。实例如下：
> java -jar -Djava.library.path=D:\ProgramFiles\opencv\opencv\build\java\x64\opencv_java460.dll
6. SpringBoot项目动态加载Dll
```java
        URL url = ClassLoader.getSystemResource("third-pard/opencv/opencv_java460.dll");
        System.load(url.getPath());
```

参考：