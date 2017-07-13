import static org.junit.Assert.*;

import org.junit.Test;

public class TestNode {
	
	private Node testNode = new Node();
	
	@Test
	public void getParentOnRoot() {
		System.out.println("getParentOnRoot");
		
		assertEquals("Root doesn't have parent", null, testNode.getParent());
	}
	
	@Test
	public void hasChild(){
		System.out.println("hasChild");
		
		assertEquals("Has no child", false, testNode.hasChild());

		testNode.setChild(new Move(1,1));
		assertEquals("Has a child", true, testNode.hasChild());
	}
	
	@Test
	public void multipleChildren(){
		System.out.println("multipleChildren");
		
		assertEquals("Has no child", 0, testNode.numberOfChildren());
		
		for(int i = 1; i < 10; i++){
			testNode.setChild(new Move(1,1));
			assertEquals("Has " + i + " children", i, testNode.numberOfChildren());
		}
		
	}
	
	@Test
	public void traverseTree(){
		System.out.println("traverseTree");
		
		for(int i = 1; i < 10; i++){
			testNode.setChild(new Move(1,1));
			testNode = testNode.getChild(0);
		}
		
		assertEquals("Current node has no children", false, testNode.hasChild());
	}
	
	@Test
	public void backpropTree(){
		System.out.println("backpropTree");
		
		for(int i = 1; i < 10; i++){
			testNode.setChild(new Move(1,1));
			testNode = testNode.getChild(0);
		}
		assertEquals("Current node has no children", false, testNode.hasChild());
		
		while(testNode.getParent() != null){
			testNode = testNode.getParent();
		}
		
		assertEquals("Current node has no parent therefore it is the root", null, testNode.getParent());
	}

}
