package cn.gelk.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 将文件夹下面的文件 打包成zip压缩文件
 * @author Administrator
 *
 */
public final class ZipUtil {
    
    /**
     * 单个文件压缩.
     * @param sourceFile 源文件
     * @param zipFile 要保存的压缩文件路径
     * @return 1 压缩成功  -1 程序异常  -2 源文件不存在
     */
    public static int zipFile(File sourceFile, File zipFile) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        
        if (sourceFile.exists() && sourceFile.isFile()) {
            try {
                if (zipFile.exists()) {
                    zipFile.delete();
                }
                
                fos = new FileOutputStream(zipFile);
                zos = new ZipOutputStream(new BufferedOutputStream(fos));
                byte[] bufs = new byte[1024 * 10];
                // 创建ZIP实体，并添加进压缩包
                ZipEntry zipEntry = new ZipEntry(sourceFile.getName());
                zos.putNextEntry(zipEntry);
                // 读取待压缩的文件并写进压缩包里
                fis = new FileInputStream(sourceFile);
                bis = new BufferedInputStream(fis, 1024 * 10);
                int read = 0;
                while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                    zos.write(bufs, 0, read);
                }
                
                return 1;
            }
            catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
            finally {
                IOUtils.closeQuietly(bis);
                IOUtils.closeQuietly(fis);
                IOUtils.closeQuietly(zos);
                IOUtils.closeQuietly(fos);
            }
        }
        else {
            return -2;
        }
    }
    
    /**
     * 将存放在targetPath目录下的源文件，打包成fileName名称的zip文件
     * 
     * @param targetPath :待压缩的文件路径
     * @param fileName :压缩后文件的名称
     * @return File
     */
    public static File zipFiles(String targetPath, String fileName) {
        File targetFile = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        
        File sourceFolder = new File(targetPath);
        if (sourceFolder.exists() && sourceFolder.isDirectory()) {
            try {
                targetFile = new File(targetPath + "/" + fileName);
                if (targetFile.exists()) {
                    targetFile.delete();
                }
                File[] sourceFiles = sourceFolder.listFiles();
                if (null != sourceFiles && sourceFiles.length > 0) {
                    fos = new FileOutputStream(targetFile);
                    zos = new ZipOutputStream(new BufferedOutputStream(fos));
                    byte[] bufs = new byte[1024 * 10];
                    for (int i = 0; i < sourceFiles.length; i++) {
                        // 创建ZIP实体，并添加进压缩包
                        ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                        zos.putNextEntry(zipEntry);
                        // 读取待压缩的文件并写进压缩包里
                        fis = new FileInputStream(sourceFiles[i]);
                        bis = new BufferedInputStream(fis, 1024 * 10);
                        int read = 0;
                        while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                            zos.write(bufs, 0, read);
                        }
                        IOUtils.closeQuietly(bis);
                        IOUtils.closeQuietly(fis);
                    }
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            finally {
                IOUtils.closeQuietly(bis);
                IOUtils.closeQuietly(fis);
                IOUtils.closeQuietly(zos);
                IOUtils.closeQuietly(fos);
            }
        }
        return targetFile;
    }
    
    /**
     * 文件打包
     * @param sourceFiles
     * @param folderName
     * @param fileName
     * @return File
     */
    public static File zipFiles(File[] sourceFiles, String folderName, String fileName) {
        File targetFile = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        
        try {
            File sourceFolder = new File(folderName);
            if (!sourceFolder.exists()) {
                sourceFolder.mkdir();
            }
            
            targetFile = new File(folderName.concat(fileName) + ".zip");
            if (targetFile.exists()) {
                targetFile.delete();
            }
            
            if (null != sourceFiles && sourceFiles.length > 0) {
                fos = new FileOutputStream(targetFile);
                zos = new ZipOutputStream(new BufferedOutputStream(fos));
                byte[] bufs = new byte[1024 * 10];
                for (int i = 0; i < sourceFiles.length; i++) {
                    // 创建ZIP实体，并添加进压缩包
                    ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                    zos.putNextEntry(zipEntry);
                    // 读取待压缩的文件并写进压缩包里
                    fis = new FileInputStream(sourceFiles[i]);
                    bis = new BufferedInputStream(fis, 1024 * 10);
                    int read = 0;
                    while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                        zos.write(bufs, 0, read);
                    }
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(zos);
            IOUtils.closeQuietly(fos);
        }
        
        return targetFile;
    }
    
    public static void main(String[] args) {
        File targetPath = new File("C:/Users/Administrator/Desktop/impAppBD201601121433007273.xls");
        File zipFile = new File("C:/Users/Administrator/Desktop/impAppBD201601121433007273.zip");
        System.out.println(ZipUtil.zipFile(targetPath, zipFile));
    }
    
}
