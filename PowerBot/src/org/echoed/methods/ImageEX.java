package org.echoed.methods;

import org.powerbot.game.api.methods.Environment;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/21/12
 * Time: 11:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageEX {


    public static void saveImage(String imageUrl, File destinationFile)
            throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }

    public static BufferedImage getImage(String fileName, String imageURL,
                                         String scriptName) {
        try {
            File dir = new File(Environment.getStorageDirectory()
                    + File.separator + "SyncScripts" + File.separator
                    + scriptName);
            File f = new File(Environment.getStorageDirectory()
                    + File.separator + "SyncScripts" + File.separator
                    + scriptName + File.separator + fileName);

            dir.mkdir();
            if (!f.exists()) {
                BufferedImage image = null;
                URL url = new URL(imageURL);
                image = ImageIO.read(url);
                ImageIO.write(image, "PNG", f);
                return image;
            }
            BufferedImage img = ImageIO.read(f);
            return img;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getLogo() {
        String LogoName = "echo_logo.png";
        String imgUrl = "http://i583.photobucket.com/albums/ss271/echoed/syncScriptLogo-1.png";
        File f = new File(Environment.getStorageDirectory()
                + File.separator + "SyncScripts" + File.separator + LogoName);
        File dir = new File(Environment.getStorageDirectory()
                + File.separator + "SyncScripts");
        try {
            dir.mkdir();
            if (!f.exists()) {
                BufferedImage image = null;
                URL url = new URL(imgUrl);
                image = ImageIO.read(url);
                ImageIO.write(image, "PNG", f);
                return image;
            }
            BufferedImage img = ImageIO.read(f);
            return img;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
