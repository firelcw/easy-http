package demo.main;


import com.github.vizaizai.EasyHttp;
import com.github.vizaizai.client.ApacheHttpClient;
import com.github.vizaizai.client.DefaultURLClient;
import com.github.vizaizai.interceptor.ErrorInterceptor;
import com.github.vizaizai.interceptor.LogInterceptor;
import demo.model.ApiResult;
import demo.model.Book;
import demo.service.BookHttpService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author liaochongwei
 * @date 2020/8/3 10:43
 */

public class BookServiceTest {
    BookHttpService bookHttpService;
    @Before
    public void init() {
        bookHttpService = EasyHttp.builder()
                                    .url("127.0.0.1:8888")
                                    .client(ApacheHttpClient.getInstance())
                                    .withInterceptor(new LogInterceptor())
                                    .withInterceptor(new ErrorInterceptor())
                                    .build(BookHttpService.class);
    }


    @Test
    public void addBook() {
        Book book = new Book();
        book.setId(uuid());
        book.setPrice(BigDecimal.valueOf(17.40));
        book.setName("零基础学Python（全彩版）2");
        book.setAuthor("明日科技(Mingri Soft)2");
        book.setDescription("Python3全新升级！超20万读者认可的彩色书，从基本概念到完整项目开发，助您快速掌握Python编程。");


        ApiResult<Void> bookRet = bookHttpService.addBook(book);
        System.out.println(bookRet.getCode());
    }
    @Test
    public void listAllBooks() {
        ApiResult<List<Book>> listApiResult = bookHttpService.listAllBooks();
        System.out.println(listApiResult.getData());
    }

    @Test
    public void editBook() {
        Book book = new Book();
        book.setId("a443257960944e45aee4da013754bdf9");
        book.setPrice(BigDecimal.valueOf(2.40));
        book.setName(" Java从入门到精通（第5版）");
        book.setAuthor("明日科技");
        book.setDescription("297个应用实例+37个典型应用+30小时教学视频+海量开发资源库，丛书累计销量200多万册,是Java入门的好图书");

        ApiResult<Void> bookRet = bookHttpService.editBook(book);
        System.out.println(bookRet.getCode());
    }


    @Test
    public void deleteBook() {

        //bookHttpService.deleteBook("47df864121ac476093105e58b3ce2ec2");
        ApiResult<String> apiResult = bookHttpService.deleteBookByBody("47df864121ac476093105e58b3ce2ec2");
        System.out.println(apiResult.getData());
    }

    @Test
    public void searchBooks() {

        Map<String,String> headers = new HashMap<>();
        headers.put("client","easy-http");
        ApiResult<List<Book>> bookRet = bookHttpService.searchBooks("Java中文文档");
        bookRet.getData().forEach(System.out::println);
    }


    @Test
    public void demo() {

        CompletableFuture<ApiResult<List<Book>>> foo = bookHttpService.foo();
        foo.thenAccept(e->System.out.println(e.getData()))
           .thenRun(()->System.out.println("异步请求执行完毕"));
        System.out.println("异步");
        foo.join();
    }

    @Test
    public void bar(){
        String[] bar = bookHttpService.bar();
        System.out.println(bar);
    }

    @Test
    public void baidu(){
        bookHttpService = EasyHttp.builder()
                                    .url("http://10.10.11.107:25068/inner")
                                    .client(DefaultURLClient.getInstance())
                                    .withInterceptor(new LogInterceptor())
                                    .withInterceptor(new ErrorInterceptor())
                                    .build(BookHttpService.class);
        CompletableFuture<String> s =  bookHttpService.baidu("dsy_Wlep4Af6LPQf","1290478984305881090");

        s.thenAccept(e->System.out.println(e))
                .thenRun(()->System.out.println("异步请求执行完毕"));
        System.out.println("异步");
        s.join();
        System.out.println(s);

    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-","");
    }

}
