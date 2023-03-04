package com.Kilakodon.kilakodon.Image;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;



/*public class ConfigImage {

    public static void saveimg(String uploaDir, String nomfile, MultipartFile multipartFile) throws IOException {

        Path UploadPath = Paths.get(uploaDir);

        if(!Files.exists(UploadPath)) {
            Files.createDirectories(UploadPath);
        }
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path fichierPath = UploadPath.resolve(nomfile);

            Files.copy(inputStream, fichierPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe){
            throw new IOException("Impossible d'enregistrer le fichier image:" + nomfile, ioe);
        }
    }
}*/
public class ConfigImage {
    public static void saveimg(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileExtension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        if (fileExtension != null && (fileExtension.equals("jpg") || fileExtension.equals("png") || fileExtension.equals("gif") || fileExtension.equals("jpeg"))) {
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) {
                throw new IOException("Impossible d'enregistrer le fichier image:" + fileName, ioe);
            }
        } else {
            throw new IllegalArgumentException("Type de fichier non pris en charge.");
        }
    }
}
    //Dans cet exemple, nous avons ajouté une vérification de l'extension de fichier en utilisant la méthode StringUtils.getFilenameExtension fournie par Spring.
// Si l'extension de fichier n'est pas prise en charge, une exception sera levée avec le message "Type de fichier non pris en charge". Vous pouvez modifier les extensions de fichiers prises en charge en modifiant les conditions if en conséquence.





