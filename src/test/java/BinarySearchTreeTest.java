

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.escuelaing.edu.BinarySearchTree;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> bst;

    @BeforeEach
    public void setUp() {
        bst = new BinarySearchTree<>();
    }

    @Test
    public void testInsertAndSearch() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        // Search for inserted values
        assertNotNull(bst.search(50));
        assertNotNull(bst.search(30));
        assertNotNull(bst.search(70));
        assertNotNull(bst.search(20));
        assertNotNull(bst.search(40));
        assertNotNull(bst.search(60));
        assertNotNull(bst.search(80));

        // Search for non-existent value
        assertNull(bst.search(90));
    }

    @Test
    public void testDeleteLeafNode() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        bst.delete(20); // Deleting a leaf node

        assertNull(bst.search(20)); // 20 should not be found anymore
    }

    @Test
    public void testDeleteNodeWithOneChild() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        bst.delete(30); // Node with one child (40)

        assertNull(bst.search(30)); // 30 should be deleted
        assertNotNull(bst.search(40)); // 40 should still be present
    }

    @Test
    public void testDeleteNodeWithTwoChildren() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        bst.delete(50); // Node with two children (30, 70)

        assertNull(bst.search(50)); // 50 should be deleted
        assertNotNull(bst.search(70)); // 70 should still be present
    }


    @Test
    public void testFindMin() {
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(1);

        assertEquals(1, bst.findMin(bst.root).value);  // El nodo mínimo es 1
    }

    @Test
    public void testFindMax() {
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(1);

        assertEquals(7, bst.findMax(bst.root).value);  // El nodo máximo es 7
    }
}

