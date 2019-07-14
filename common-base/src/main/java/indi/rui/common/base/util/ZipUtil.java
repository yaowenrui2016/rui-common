package indi.rui.common.base.util;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩工具类
 *
 */
public class ZipUtil {
    public static final int BUFFER_SIZE = 512;

    public static  void zipFile(List<File> sources, File target) throws IOException {
        OutputStream out = null;
        ZipOutputStream zout = null;
        try {
            out = new FileOutputStream(target);
            zout = new ZipOutputStream(out);
            byte[] buf = new byte[BUFFER_SIZE];

            for (File file : sources) {
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zipEntry.setSize(file.length());
                zout.putNextEntry(zipEntry);

                BufferedInputStream in = null;
                try {
                    in = new BufferedInputStream(new FileInputStream(file));
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        zout.write(buf, 0, len);
                    }
                } finally {
                    if (in != null) {
                        in.close();
                    }
                }
            }
        } finally {
            try {
                if (zout != null) {
                    zout.close();
                }
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
    }
}
