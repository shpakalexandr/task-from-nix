package com.nix.testtask.breadcrumbs;

public class Breadcrumbs {

	private Node parent1;
	private Node parent2;
	private Node current;
	public Node getParent1() {
		return parent1;
	}
	public void setParent1(Node parent1) {
		this.parent1 = parent1;
	}
	public Node getParent2() {
		return parent2;
	}
	public void setParent2(Node parent2) {
		this.parent2 = parent2;
	}
	public Node getCurrent() {
		return current;
	}
	public void setCurrent(Node current) {
		this.current = current;
	}
}