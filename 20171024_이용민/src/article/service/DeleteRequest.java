package article.service;

public class DeleteRequest {

	private int articleNumber;

	public DeleteRequest(int articleNumber) {
		super();
		this.articleNumber = articleNumber;
	}

	public int getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}
}
