package dev.midnightcoder;

import dev.midnightcoder.albums.IAlbumImportService;
import dev.midnightcoder.comments.ICommentImportService;
import dev.midnightcoder.photos.IPhotoImportService;
import dev.midnightcoder.posts.IPostImportService;
import dev.midnightcoder.users.IUserImportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class Application {

    void main() {
        SpringApplication.run(Application.class);
    }

    @Bean
    RestClient restClient() {
        return RestClient.builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .build();
    }

//    @Bean
//    CommandLineRunner commandLineRunner(
//        IPostImportService postImportService,
//        ICommentImportService commentImportService,
//        IUserImportService userImportService,
//        IAlbumImportService albumImportService,
//        IPhotoImportService photoImportService
//    ) {
//        return args -> {
//            postImportService.importPosts();
//            commentImportService.importComments();
//            userImportService.importUsers();
//            albumImportService.importAlbums();
//            photoImportService.importPhotos();
//        };
//    }
}
