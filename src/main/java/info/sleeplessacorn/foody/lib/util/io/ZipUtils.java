package info.sleeplessacorn.foody.lib.util.io;

import org.apache.commons.io.FilenameUtils;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtils {

    /**
     * Extracts a zip archive to the given directory.
     *
     * @param zip - The zip to extract
     * @return the directory extracted into
     */
    @Nonnull
    public static File extractZip(File zip, File extractTo) {
        extractTo.mkdir();

        ZipFile zipFile = null;

        try {
            zipFile = new ZipFile(zip);

            // get an enumeration of the ZIP file entries
            Enumeration<? extends ZipEntry> e = zipFile.entries();

            while (e.hasMoreElements()) {
                ZipEntry entry = e.nextElement();

                File destinationPath = new File(extractTo, entry.getName());

                // create parent directories
                destinationPath.getParentFile().mkdirs();

                // if the entry is a directory, continue
                if (entry.isDirectory())
                    continue;

                BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));

                int b;
                byte buffer[] = new byte[1024];

                FileOutputStream fos = new FileOutputStream(destinationPath);

                BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);

                while ((b = bis.read(buffer, 0, 1024)) != -1) {
                    bos.write(buffer, 0, b);
                }

                bos.close();
                bis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (zipFile != null) {
                    zipFile.close();
                }
                zip.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return extractTo;
    }

    /**
     * Extracts a zip archive to a directory of the same name in the same folder.
     *
     * Eg:
     * {@code foo/bar.zip} -> {@code foo/bar}
     *
     * @param zipFile - The zip to extract
     * @return the directory extracted into
     */
    @Nonnull
    public static File extractZip(File zipFile) {
        return extractZip(zipFile, new File(zipFile.getParentFile(), FilenameUtils.getBaseName(zipFile.getName())));
    }
}
