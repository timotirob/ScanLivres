import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.List;

public class ListeFichier {
	
	private int nbLivresBerto = 0;
	
	public void listeRep(String racine)
	{
		
		List<String> stocks = new ArrayList<>();
		
		
		FileVisitor<Path> myFileVisitor = new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			throws IOException {
					
	
			System.out.println("Visited File: "+file.toString());
		
			
			return FileVisitResult.CONTINUE;
			}
			};
			FileSystem fileSystem = FileSystems.getDefault();
			
			Path directory= fileSystem.getPath(racine);
			
			try {
			Files.walkFileTree(directory, myFileVisitor);
			} catch (IOException e) {
			e.printStackTrace();
			}	
		
	}
	
	public void listeDoublons(String racine, String exclusionRep)
	{
		List<String> stocks = new ArrayList<>();
		
		
		FileVisitor<Path> myFileVisitor = new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			throws IOException {
			
		
				String delims = "\\\\";
				String[] tokens = file.toString().split(delims);
				String nomFile = tokens[tokens.length - 1] ;
				//System.out.println("Visited File: "+nomFile);

			if (stocks.contains(nomFile)  && !nomFile.equals("desktop.ini") && !nomFile.equals(".dropbox"))
					{
					System.out.println("Doublon: "+nomFile + " Dernier répertoire: " + file.toString());
					}
			else if 
			(file.toString().contains(exclusionRep))
				{
				//System.out.println("Pas de parcours pour: "+nomFile);
				}
			else
			{
			stocks.add(nomFile);
			nbLivresBerto ++ ;
			}
			
			
			
			return FileVisitResult.CONTINUE;
			}
			};
			FileSystem fileSystem = FileSystems.getDefault();
			
			Path directory= fileSystem.getPath(racine);
			
			try {
			Files.walkFileTree(directory, myFileVisitor);
			} catch (IOException e) {
			e.printStackTrace();
			}	
	}
	/***
	Using the NIO.2, create a FileVisitor object and perform a desired implementation within its visitFile method.
	Next, obtain the default FileSystem object and grab a reference to the Path that you’d like to scan via the getPath()
	method. Lastly, invoke the Files.walkFileTree() method, passing the Path and the FileVisitor that you created.
	The following code demonstrates how to perform these tasks.
	***/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ListeFichier test = new ListeFichier();
		test.listeDoublons("F:\\Dropbox\\Dropbox\\Livres","Source Code") ;
		System.out.println("Vous avez "+ test.nbLivresBerto + " livres o mon maitre !" ) ;
		//test.listeRep("H:\\BTSSIO\\Livres") ;
		
		
	}

}
