import java.util.Iterator;
import java.util.Vector;
class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E>
{
    private Vector<E> vector; // A growable array of objects
    private void traverse(Node<E> curr) // Reference to the tree/subtree, the root
    {
        if (curr != null)
        {
            traverse(curr.left);
            vector.add(curr.data); // Add tree data values to vector while nodes are not null
            traverse(curr.right);
        }
    }


    public Iterator<E> iterator()
    {
        vector = new Vector<E>();
        traverse(root);
        return vector.iterator();
    }


    private Node<E> findIOP(Node<E> curr) // Reference to the tree/subtree, the root
    {
        curr = curr.left; // Assign curr the value of the left subtree of the argument
        while (curr.right != null)
        {
            curr = curr.right; // Assign curr the value of the rightmost node in the left subtree
        }
        return curr;
    }


    public int height()
    {
        return height(root); // Finds the height of the tree/subtree
    }
    private int height(Node<E> curr) // Reference to the tree/subtree, the root
    {
        if (curr == null)
        {
            return 0;
        }
        int left = height(curr.left) + 1;
        int right = height(curr.right) + 1;
        return left > right ? left : right; // The height of the whole tree (or subtree) can be found by looking at its right and left subtrees, choosing the greater height and adding 1
    }


    public void insert(E data)
    {
        Node<E> temp = new Node<E>(data);
        root = insert(root, temp); // Aim to call private method 'insert' in order to update bst, and return updated reference to beginning of bst
    }


    private Node<E> insert(Node<E> curr, Node<E> node) // curr as node being used to traverse bst, node as node being inserted
    {
        if (curr == null) // If there is no tree
        {
            return node; // Insert node
        }
        else if (node.data.compareTo(curr.data) <= 0) // Otherwise, if the data to be inserted is less than the data at the node being traversed
        {
            curr.left = insert(curr.left, node); // Insert at the left subtree
        }
        else // Otherwise, if the data to be inserted is greater than the data at the node being traversed
        {
            curr.right = insert(curr.right, node); // Insert at the right subtree
        }


        return curr; // Return reference to head after updating bst
    }


    public void remove(E data)
    {
        root = remove(root, data); // Aim to call private method 'remove' in order to update bst, and return updated reference to beginning of bst
    }


    private Node<E> remove(Node<E> curr, E data) // curr as node being used to traverse bst, data as data element being removed
    {
        if (curr == null) // If tree is empty
        {
            return null; // Return null
        }
        else // If tree is not empty
        {
            if (data.compareTo(curr.data) < 0) // If data is less than traversed node's data
            {
                curr.left = remove(curr.left, data); // Remove at the left subtree
            }
            else if (data.compareTo(curr.data) > 0) // If data is greater than traversed node's data
            {
                curr.right = remove(curr.right, data); // Remove at the right subtree
            }
            else // Data is equal to the traversed node's data
            {
                if (curr.left == null) // If traversed node has no left child
                {
                    return curr.right; // Return right child
                }
                else if (curr.right == null) // If traversed node has no right child
                {
                    return curr.left; // Return right child
                }
                else // Traversed node has two children
                {
                    // Assign the data at the traversed node with its iop's data
                    curr.data = findIOP(curr).data;
                    // Remove the data of the IOP from the left subtree of the traversed node
                    curr.left = remove(curr.left, curr.data);
                }
            }
        }
        return curr; // Return reference to root after updating bst
    }


    public boolean search(E data)
    {
        return search(root, data); // Aim to call private method 'search' in order to check if data is within bst
    }


    private boolean search(Node<E> curr, E data) // curr as node being used to traverse bst, data as element being searched for
    {
        if (curr == null) // If there is no tree
        {
            return false; // Return false
        }
        else // Otherwise if there is a tree
        {
            if (data.compareTo(curr.data) == 0) // If data being searched for is the same as the data of the traversed node
            {
                return true; // Return true
            }
            else if (data.compareTo(curr.data) < 0) // Otherwise if the data being searched for is less than the data being traversed
            {
                return search(curr.left, data); // Search the left subtree
            }
            else // Otherwise the data being searched for is greater than the data of the traversed node
            {
                return search(curr.right, data); // Search the right subtree
            }
        }
    }
}
