import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Nikolai Sazonov, Nikola Stanic
 */
public class ImageCompression extends Application {

	// size of displayed image
	public static final int WIDTH = 400, HEIGHT = 300;

	// uniform color threshold (range 0-1) - if the difference in any color
	// component is less than the threshold, the colors are considered to be
	// uniform
	private static final double THRESHOLD = .25;

	// quad tree node
	private static class QuadTreeNode {

		// region covered by this subtree - upper left corner (x,y), dimensions wxh
		private int x_, y_, w_, h_;

		private Color color_;
		private QuadTreeNode[] children_;

		QuadTreeNode ( int x, int y, int w, int h, Color color ) {
			x_ = x;
			y_ = y;
			w_ = w;
			h_ = h;
			color_ = color;
			children_ = null;
		}

		QuadTreeNode ( int x, int y, int w, int h, QuadTreeNode[] children ) {
			x_ = x;
			y_ = y;
			w_ = w;
			h_ = h;
			color_ = null;
			children_ = children;
		}

		void fillRegion ( GraphicsContext g ) {

			if ( color_ == null ) {
				return;
			}
			g.setFill(color_);
			g.fillRect(x_,y_,w_,h_);
		}

		boolean isLeaf () {
			return children_ == null;
		}

		QuadTreeNode[] getChildren () {
			return children_;
		}

	}

	/**
	 * Build a quadtree representing the compressed image.
	 * 
	 * @param pixels
	 *          the pixels of the image to compress
	 * @param w
	 *          image width (in pixels)
	 * @param h
	 *          image height (in pixels)
	 * @return root of the quadtree
	 */
	public QuadTreeNode buildQuadTree ( PixelReader pixels, int w, int h ) {
		return buildQuadTree(pixels, 0, 0, w, h);
	}

	/**
	 * Build a quadtree representing the compressed form of the specified region
	 * of the image.
	 * 
	 * @param pixels
	 *          the pixels of the image to compress
	 * @param x
	 *          left side of region
	 * @param y
	 *          top of region
	 * @param w
	 *          region width (in pixels)
	 * @param h
	 *          region height (in pixels)
	 * @return root of the quadtree
	 */

	private QuadTreeNode buildQuadTree ( PixelReader pixels, int x, int y, int w, int h ) {
		
		if(isUniformColor(pixels, x, y, w, h, THRESHOLD)) {
		
			Color avg = getAverageColor(pixels, x, y, w, h);
			return new QuadTreeNode(x, y, w, h, avg);
		
		}
		
		// divide region into four quadrants
		QuadTreeNode[] children = 
			{
			 	buildQuadTree(pixels, x, y, w/2, h/2), // topL
			 	buildQuadTree(pixels, x + w/2, y, w - w/2, h/2), // topR
			 	buildQuadTree(pixels, x, y + h/2, w/2, h - h/2), // botL
			 	buildQuadTree(pixels, x + w/2, y + h/2, w - w/2, h - h/2) // botR
			};

		return new QuadTreeNode(x, y, w, h, children );
	}

	/**
	 * Draw the compressed image represented by the quadtree.
	 * 
	 * @param root
	 *          root of the quadtree
	 * @param g
	 *          the GraphicsContext for drawing
	 */
	public void draw ( QuadTreeNode root, GraphicsContext g ) {
		
		if(root.isLeaf()) {
			root.fillRegion(g);
		}
		else {
			for( QuadTreeNode child : root.children_ ) {
				draw(child, g);
			}
		}
		
	}

	/**
	 * Determine if the pixels in the specified region are a uniform color
	 * according to the specified threshold.
	 * 
	 * @param pixels
	 *          image pixels
	 * @param x
	 *          left side of region
	 * @param y
	 *          top of region
	 * @param w
	 *          width of region
	 * @param h
	 *          height of region
	 * @param threshold
	 *          color difference threshold (range 0-1)
	 * @return true if all of the pixels of the region are within the threshold of
	 *         the region's average color, false otherwise
	 */
	private boolean isUniformColor ( PixelReader pixels, int x, int y, int w,
	                                 int h, double threshold ) {
		Color avg = getAverageColor(pixels,x,y,w,h);
		for ( int px = x ; px < x + w ; px++ ) {
			for ( int py = y ; py < y + h ; py++ ) {
				Color color = pixels.getColor(px,py);
				if ( Math.abs(color.getRed() - avg.getRed()) > threshold ) {
					return false;
				}
				if ( Math.abs(color.getGreen() - avg.getGreen()) > threshold ) {
					return false;
				}
				if ( Math.abs(color.getBlue() - avg.getBlue()) > threshold ) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Get the average color of the region.
	 * 
	 * @param pixels
	 *          image pixels
	 * @param x
	 *          left side of region
	 * @param y
	 *          top of region
	 * @param w
	 *          width of region
	 * @param h
	 *          height of region
	 * @return the average pixel color in the region
	 */
	private Color getAverageColor ( PixelReader pixels, int x, int y, int w,
	                                int h ) {
		double r = 0, g = 0, b = 0;
		int count = 0;
		for ( int px = x ; px < x + w ; px++ ) {
			for ( int py = y ; py < y + h ; py++ ) {
				Color color = pixels.getColor(px,py);
				r += color.getRed();
				b += color.getBlue();
				g += color.getGreen();
				count++;
			}
		}
		return new Color(r / count,g / count,b / count,1.0);
	}

	/**
	 * Fill a rectangle with the specified color, drawn on the specified
	 * GraphicsContext.
	 * 
	 * @param g
	 *          GraphicsContext for where to draw
	 * @param x
	 *          left side of region
	 * @param y
	 *          top of region
	 * @param w
	 *          width of region
	 * @param h
	 *          height of region
	 * @param color
	 *          fill color
	 */
	private void fillRegion ( GraphicsContext g, int x, int y, int w, int h,
	                          Color color ) {
		g.setFill(color);
		g.fillRect(x,y,w,h);
	}

	public static void main ( String[] args ) {
		launch(args);
	}

	@Override
	public void start ( Stage stage ) {
		stage.setTitle("Image Compression");

		GridPane root = new GridPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);

		// image to compress
		Image image = new Image("file:img_4174.jpg",WIDTH,HEIGHT,true,true);
		ImageView imgview = new ImageView();
		imgview.setImage(image);

		// drawing area for compressed image
		Canvas canvas = new Canvas(image.getWidth(),image.getHeight());

		root.add(imgview,0,0);
		root.add(canvas,1,0);

		stage.show();

		QuadTreeNode quadtree =
		    buildQuadTree(image.getPixelReader(),(int) image.getWidth(),
		                  (int) image.getHeight());
		draw(quadtree,canvas.getGraphicsContext2D());
	}

}
