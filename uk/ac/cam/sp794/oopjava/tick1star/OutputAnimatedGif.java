package uk.ac.cam.sp794.oopjava.tick1star;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.FileImageOutputStream;

public class OutputAnimatedGif {
	private FileImageOutputStream output;
	private ImageWriter writer;
        private static int[] pixel_color = {0,0,0};
        private final int delta = 8;

	public OutputAnimatedGif(String file) throws IOException {
		this.output = new FileImageOutputStream(new File(file)); 
		this.writer = ImageIO.getImageWritersByMIMEType("image/gif").next();
		this.writer.setOutput(output);
		this.writer.prepareWriteSequence(null);
	}

	private BufferedImage makeFrame(boolean[][] world) {
		int height = world.length;
		int width = world[0].length;
		final int scale = 10;
		BufferedImage image = new BufferedImage(width * scale + 1, height * scale + 1, BufferedImage.TYPE_INT_RGB);
		java.awt.Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
                g.fillRect(0, 0, width * scale, height * scale);
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				if (world[y][x]) {
                                        pixel_color[0]+=delta;
                                        if(pixel_color[0]>=256){
                                                pixel_color[0]=0;
                                                pixel_color[1]+=delta;
                                                if(pixel_color[1]>=256){
                                                        pixel_color[1]=0;
                                                        pixel_color[2]+=delta;
                                                        if(pixel_color[2]>=256)
                                                                pixel_color[2]=0;
                                                }
                                        }
                                        String rgb = Integer.toHexString(pixel_color[0])+Integer.toHexString(pixel_color[1])+Integer.toHexString(pixel_color[2]);
                                        while(rgb.length()<6)
                                                rgb = "0"+rgb;
                                        //System.out.println(rgb);
					g.setColor(java.awt.Color.decode("0x"+rgb));
					g.fillRect(x * scale, y * scale, scale, scale);
				}
			}
		}
                

		g.setColor(Color.LIGHT_GRAY);
		for (int x = 0; x <= width * scale; x += scale) {
			g.drawLine(x, 0, x, height * scale);
		}

		for (int y = 0; y <= height * scale; y += scale) {
			g.drawLine(0, y, width * scale, y);
		}
                g.dispose();
		return image;
	}
	
	public void addFrame(boolean[][] world) throws IOException {
		BufferedImage image = makeFrame(world);
		try {
			IIOMetadataNode node = new IIOMetadataNode("javax_imageio_gif_image_1.0");
			IIOMetadataNode extension = new IIOMetadataNode("GraphicControlExtension");
			extension.setAttribute("disposalMethod", "none");
			extension.setAttribute("userInputFlag", "FALSE");
			extension.setAttribute("transparentColorFlag", "FALSE");
			extension.setAttribute("delayTime", "1");
			extension.setAttribute("transparentColorIndex", "255");
			node.appendChild(extension);
			IIOMetadataNode appExtensions = new IIOMetadataNode("ApplicationExtensions");
			IIOMetadataNode appExtension = new IIOMetadataNode("ApplicationExtension");
			appExtension.setAttribute("applicationID", "NETSCAPE");
			appExtension.setAttribute("authenticationCode", "2.0");
			appExtension.setUserObject("\u0021\u00ff\u000bNETSCAPE2.0\u0003\u0001\u0000\u0000\u0000".getBytes());
			appExtensions.appendChild(appExtension);
			node.appendChild(appExtensions);

			IIOMetadata metadata = writer.getDefaultImageMetadata(new ImageTypeSpecifier(image), null);
			metadata.mergeTree("javax_imageio_gif_image_1.0", node);

			IIOImage t = new IIOImage(image, null, metadata);
			writer.writeToSequence(t, null);
		}
		catch (IIOInvalidTreeException e) {
			throw new IOException(e);
		}
	}

	public void close() throws IOException {
		writer.endWriteSequence();
	}

}
