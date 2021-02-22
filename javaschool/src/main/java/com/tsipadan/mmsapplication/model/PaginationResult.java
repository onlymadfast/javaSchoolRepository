//package com.tsipadan.mmsapplication.model;
//
//import lombok.Getter;
//import org.hibernate.ScrollMode;
//import org.hibernate.ScrollableResults;
//import org.hibernate.query.Query;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PaginationResult<E> {
//
//  @Getter
//  private final int totalRecords;
//  @Getter
//  private final int currentPage;
//  @Getter
//  private final List<E> list;
//  @Getter
//  private final int maxResult;
//  @Getter
//  private final int totalPages;
//  @Getter
//  private List<Integer> navigationPages;
//
//  private int maxNavigationPage;
//
//  public PaginationResult(Query<E> query, int page, int maxResult, int maxNavigationPage) {
//
//    final int pageIndex = Math.max(page - 1, 0);
//    int fromRecordIndex = pageIndex * maxResult;
//    int maxRecordIndex = fromRecordIndex + maxResult;
//
//    ScrollableResults resultScroll = query.scroll(ScrollMode.SCROLL_INSENSITIVE);
//    List<E> results = new ArrayList<>();
//
//    boolean hasResult = resultScroll.first();
//    if (hasResult) {
//      hasResult = resultScroll.scroll(fromRecordIndex);
//      if (hasResult) {
//        do {
//          E record = (E) resultScroll.get(0);
//          results.add(record);
//        } while (resultScroll.next()
//            && resultScroll.getRowNumber() >= fromRecordIndex
//            && resultScroll.getRowNumber() < maxRecordIndex);
//      }
//      resultScroll.last();
//    }
//
//    this.totalRecords = resultScroll.getRowNumber() + 1;
//    this.currentPage = pageIndex + 1;
//    this.list = results;
//    this.maxResult = maxResult;
//
//    if (this.totalRecords % this.maxResult == 0) {
//      this.totalPages = this.totalRecords / this.maxResult;
//    } else {
//      this.totalPages = (this.totalRecords / this.maxResult) + 1;
//    }
////    this.maxNavigationPage = maxNavigationPage;
//    if (maxNavigationPage < totalPages) {
//      this.maxNavigationPage = maxNavigationPage;
//    }
//    resultScroll.close();
//    this.calcNavigationPages();
//  }
//
//  private void calcNavigationPages() {
//    this.navigationPages = new ArrayList<>();
//    int current = this.currentPage > this.totalPages ? this.totalPages : this.currentPage;
//    int begin = current - this.maxNavigationPage / 2;
//    int end = current + this.maxNavigationPage / 2;
//
//    navigationPages.add(1);
//    if (begin > 2) {
//      navigationPages.add(-1);
//    }
//    for (int i = begin; i < end; i++) {
//      if (i > 1 && i < this.totalPages) {
//        navigationPages.add(i);
//      }
//    }
//    if (end < this.totalPages - 2) {
//      navigationPages.add(-1);
//    }
//    navigationPages.add(this.totalPages);
//  }
//
//}
