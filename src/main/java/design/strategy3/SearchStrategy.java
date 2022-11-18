package design.strategy3;

import design.strategy3.adapter.FindAlgorithm;

interface SearchStrategy {
	public void search();
}

class SearchStrategyAll implements SearchStrategy {
	@Override
	public void search() {
		System.out.println("Search All");
	}
}

class SearchStrategyImage implements SearchStrategy {
	@Override
	public void search() {
		System.out.println("Search Image");
	}
}

class SearchStrategyNews implements SearchStrategy {
	@Override
	public void search() {
		System.out.println("Search News");
	}
}

class SearchStrategyMap implements SearchStrategy {
	@Override
	public void search() {
		System.out.println("Search Map");
	}
}

class SearchFindAdapter implements SearchStrategy {
	private FindAlgorithm findAlgorithm;

	public SearchFindAdapter (FindAlgorithm findAlgorithm) {
		this.findAlgorithm = findAlgorithm;
	}

	@Override
	public void search() {
		findAlgorithm.find(true);
	}
}
