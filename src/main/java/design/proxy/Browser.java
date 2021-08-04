package design.proxy;

public class Browser implements IBrowser{

	private String url;

	public Browser(String url) {
		this.url = url;
	}

	@Override
	public Html show() {
		System.out.println("borwser loading html from : " + url);
		return new Html(url);
	}
}
