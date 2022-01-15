package com.irishinterest.irishinterest.webservice;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {

    // AUTHORS

    @GET("?value=authors&type=getAll&offset=0")
    Flowable<List<Author>> authors();

    @GET("?value=authors&type=searchByName")
    Flowable<List<Author>> authorsSearchByName(
            @Query("searchBy") String name
    );

    @GET("?value=authors&type=byLastNameStartsWith")
    Flowable<List<Author>> authorsByLetter(
            @Query("startsWith") String letter
    );

    @GET("?value=authors&type=count")
    Flowable<Integer> authorsCount();
    //.catchAndReturn(42499)

    @GET("?value=authors&type=abcCount")
    Flowable<List<CountByLetter>> authorsAtoZCount();
    //return session.rx.data(request: request).map { (data: Data) -> [CountByLetter] in
    //            let counts: [CountByLetter] = try decode(data: data)
    //            return counts.filter { (countBy: CountByLetter) -> Bool in
    //                countBy.alpha.first?.isLetter == true
    //            }

    @GET("?value=authors&type=detailsById")
    Flowable<AuthorDetails> authorDetails(
            @Query("authorId") Integer authorId
    );
    //}).catchAndReturn(AuthorDetails.empty())

    @GET("?value=authors&type=detailsById")
    Flowable<ArrayMap<String, ArrayList<Author>>> authorsByBookIds(
            @Query("ids") List<Integer> bookIds
    );
//    func authors(byBookIds: [Int]) -> Observable<AuthorsOfBooks> {
//        let uniqueIds = Set(byBookIds)
//        let paramIds: String = uniqueIds.sorted().map{ String($0) }.joined(separator: ",")
//        let params: String = "?value=authors&type=byBookIds&apiKey=testApiKey&ids=\(paramIds)"
//          ...
//        }).catchAndReturn([:])
//    func authors(ofBooks observable: Observable<[Book]>) -> Observable<AuthorsOfBooks> {
//        observable.map { (books: [Book]) in
//            books.map { $0.id }
//        }.flatMap { bookIds in
//            authors(byBookIds: bookIds)
//        }
//    }


    // CATEGORIES

    @GET("?value=categories")
    Flowable<List<Category>> categories();


    // BOOKS
    @GET("?value=books&type=searchByName")
    Flowable<List<Book>> booksSearchByName(
            @Query("searchBy") String name
    );

    @GET("?value=books&type=byAuthorID")
    Flowable<List<Book>> booksByAuthorID(
            @Query("authorID") Integer authorID,
            @Query("offset") Integer offset
    );
//    func books(byAuthorID authorID: Int, page: Int) -> Observable<[Book]> {
//        let params: String = "?value=books&type=byAuthorID&authorID=\(authorID)&offset=\(page * Const.pageSize)&apiKey=testApiKey"

    @GET("?value=books&type=byCategory")
    Flowable<List<Book>> booksByCategoryId(
            @Query("authorID") Integer categoryId,
            @Query("offset") Integer offset
    );
//    func books(byCategoryId: Int, page: Int) -> Observable<[Book]> {
//        let params: String = "?value=books&type=byCategory&categoryId=\(byCategoryId)&offset=\(page * Const.pageSize)&apiKey=testApiKey"

    @GET("?value=books&type=getLatest2")
    Flowable<List<Book>> booksLatest(
            @Query("offset") Integer offset
    );
//    func booksLatest(page: Int) -> Observable<[Book]> {
//        let params: String = "?value=books&type=getLatest2&apiKey=testApiKey&offset=\(page * Const.pageSize)"

    @GET("?value=books&type=getPublished")
    Flowable<List<Book>> booksPublished(
            @Query("offset") Integer offset
    );
//    func booksPublished(page: Int) -> Observable<[Book]> {
//        let params: String = "?value=books&type=getPublished&apiKey=testApiKey&offset=\(page * Const.pageSize)"

    @GET("?value=books&type=getComingSoon")
    Flowable<List<Book>> booksComingSoon(
            @Query("offset") Integer offset
    );
//    func booksComingSoon(page: Int) -> Observable<[Book]> {
//        let params: String = "?value=books&type=getComingSoon&apiKey=testApiKey&offset=\(page * Const.pageSize)"

    @GET("?value=books&type=getById2")
    Flowable<BookDetails> bookDetails(
            @Query("bookId") Integer bookID
    );
//    func details(bookID: Int) -> Observable<BookDetails> {
//        let params: String = "?value=books&type=getById2&apiKey=testApiKey&bookId=\(bookID)"
//        }).catchAndReturn(BookDetails.empty())

}
