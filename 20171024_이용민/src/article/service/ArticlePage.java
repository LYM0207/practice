package article.service;

import java.util.List;

import article.model.Article;

public class ArticlePage {

	private int total;
	private int currentPage;
	private List<Article> content;
	private int totalPages;
	private int startPage;
	private int endPage;

	public ArticlePage(int total, int currentPage, int size,
			List<Article> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;

		// 게시글 개수가 0개이면, 모두 0으로 할당
		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			// 게시글 개수를 이용하여 전체 페이지 개수 구한다
			totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if (modVal == 0) {
				startPage -= 5;
			}

			endPage = startPage + 4;
			if (endPage > totalPages) {
				endPage = totalPages;
			}
		}
	}

	public boolean hasNoArticles() {
		return total == 0;
	}

	public boolean hasArticles() {
		return total > 0;
	}

	public int getTotal() {
		return total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Article> getContent() {
		return content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

}
