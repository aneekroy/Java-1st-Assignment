import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class File_compress
{
    private  String OUTPUT_GZIP_FILE;
    private  String SOURCE_FILE;
 
 
    File_compress(String a,String b){
	
	  OUTPUT_GZIP_FILE= b;
	  SOURCE_FILE = a;
    }
/*compressing a file using gzip compression format*/
    /*public static void gzipFile()
    {
        byte[] buffer = new byte[1024];
 
     try{
 
    	GZIPOutputStream gzos = 
    		new GZIPOutputStream(new FileOutputStream(OUTPUT_GZIP_FILE));
 
        FileInputStream in = 
            new FileInputStream(SOURCE_FILE);
 
        int len;
        while ((len = in.read(buffer)) > 0) {
        	gzos.write(buffer, 0, len);
        }
 
        in.close();
    	
    	gzos.finish();
    	gzos.close();
    	System.out.println("Done");
    	
    }catch(IOException ex){
       ex.printStackTrace();   
    }
   }*/

    public static void main(String[] args) throws IOException {
		File directoryToZip = new File(args[0]);

		List<File> fileList = new ArrayList<File>();
		System.out.println("---Getting references to all files in: " + directoryToZip.getCanonicalPath());
		getAllFiles(directoryToZip, fileList);
		System.out.println("---Creating zip file");
		zipDirectory(directoryToZip, fileList);
		System.out.println("---Done");
	}

   public static void getAllFiles(File dir, List<File> fileList) {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				fileList.add(file);
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());
					//getAllFiles(file, fileList);
				} else {
					System.out.println("     file:" + file.getCanonicalPath());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

public static void zipDirectory(File directoryToZip, List<File> fileList) {

		try {
			FileOutputStream fos = new FileOutputStream(directoryToZip.getName() + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (File file : fileList) {
				if (!file.isDirectory()) { // we only zip files, not directories
					addToZip(directoryToZip, file, zos);
				}
			}

			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
			IOException {

		FileInputStream fis = new FileInputStream(file);

		// we want the zipEntry's path to be a relative path that is relative
		// to the directory being zipped, so chop off the rest of the path
		String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
				file.getCanonicalPath().length());
		System.out.println("Writing '" + zipFilePath + "' to zip file");
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}

}
 

