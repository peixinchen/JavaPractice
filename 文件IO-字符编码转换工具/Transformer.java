import java.io.*;
import java.nio.charset.Charset;

public class Transformer {
    private static void usage() {
        System.err.println("usage: Transformer <from 编码> <to 编码> 文件路径");
        System.err.println("支持的字符编码为：UTF-8 和 GB18030");
    }

    private static Charset forName(String name) {
        switch (name) {
            case "utf-8":
                return Charset.forName("UTF-8");
            case "gb18030":
                return Charset.forName("GB18030");
            default:
                usage();
                System.exit(1);
        }

        return null;
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            usage();
            System.exit(1);
        }

        Charset from = forName(args[0].toLowerCase());
        Charset to = forName(args[1].toLowerCase());

        File file = new File(args[2]);
        if (!file.exists()) {
            System.err.println("找不到文件: " + args[2]);
            System.exit(1);
        }

        if (!file.isFile()) {
            System.err.println("不是普通文件: " + args[2]);
            System.exit(1);
        }

        try {
            InputStreamReader input = new InputStreamReader(
                    new FileInputStream(file), from
            );

            OutputStreamWriter output = new OutputStreamWriter(
                    new FileOutputStream(args[2] + ".out"), to
            );

            char[] buf = new char[1024];
            int n;
            while ((n = input.read(buf, 0, 1024)) != -1) {
                output.write(buf, 0, n);
            }

            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
