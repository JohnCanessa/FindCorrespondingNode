import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * 1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree
 * https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
 */
public class FindCorrespondingNode {


    /**
     * Return a reference to the same node in the cloned tree.
     * Recursive call.
     */
    static void getTargetCopy1(TreeNode root, TreeNode target, TreeNode[] found) {
        if (root != null) {

            // **** traverse left sub tree ****
            getTargetCopy1(root.left, target, found);

            // **** check if values match ****
            if (root.val == target.val)
                found[0] = root;

            // **** traverse right sub tree ****
            getTargetCopy1(root.right, target, found);
        }
    }


    /**
     * Return a reference to the same node in the cloned tree.
     * 
     * Runtime: 2 ms, faster than 58.89% of Java online submissions.
     * Memory Usage: 113.4 MB, less than 12.23% of Java online submissions.
     */
    static TreeNode getTargetCopy1(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        
        // **** sanity checks ****
        if (cloned.val == target.val)
            return cloned;

        // **** initialization ****
        TreeNode[] found = new TreeNode[1];

        // **** look for target in the cloned tree ****
        getTargetCopy1(cloned, target, found);

        // **** return node ****
        return found[0];
    }


    /**
     * Return a reference to the same node in the cloned tree.
     * Recursive call.
     * 
     * Runtime: 1 ms, faster than 96.59% of Java online submissions.
     * Memory Usage: 113.4 MB, less than 12.23% of Java online submissions.
     */
    static TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        
        // **** sanity checks ****
        if (original == null || cloned.val == target.val)
            return cloned;

        // **** visit left sub tree ****
        TreeNode node = getTargetCopy(original.left, cloned.left, target);

        // **** check if we found target node ****
        if (node != null)
            return node;

        // **** visit right sub tree ****
        return getTargetCopy(original.right, cloned.right, target);
    }


    /**
     * Test scaffolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read tree node values into a Integer[] (does not allow null values) ****
        // Integer[] arr = Stream.of(br.readLine().trim().split(","))
        //                         .mapToInt(Integer::parseInt)
        //                         .boxed()
        //                         .toArray(Integer[]::new);

        // **** read tree node values into a String[] (allows null values) ****
        String[] strArr = br.readLine().trim().split(",");

        // **** read traget node value ****
        int targetVal = Integer.parseInt(br.readLine().trim());

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<<    strArr: " + Arrays.toString(strArr));

        // **** create and populate Integer[] ****
        Integer[] arr = new Integer[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals("null") == false)
                arr[i] = Integer.parseInt(strArr[i]);
            else
                arr[i] = null;
        }

        // ???? ????
        System.out.println("main <<<       arr: " + Arrays.toString(arr));
        System.out.println("main <<< targetVal: " + targetVal);

        // **** create and populate a original binary tree ****
        BST original = new BST();
        original.root = original.populate(arr);
 
        // ???? ????
        System.out.println("main <<< original levelOrder:");
        System.out.println(original.levelOrder());

        // **** create and clone the original binary tree ****
        BST cloned = new BST();
        cloned.root = cloned.cloneTree(original.root);

        // ???? ????
        System.out.println("main <<<   cloned levelOrder:");
        System.out.println(cloned.levelOrder());

        // **** get node with the specified value ****
        TreeNode target = original.findBT(original.root, targetVal);
        if (target != null)
            System.out.println("main <<<         target: " + target.toString());
        else {
            System.out.println("main <<<         target: null");
            System.exit(-1);
        }

        // **** get the target node from the clonned tree ****
        TreeNode node = getTargetCopy1(original.root, cloned.root, target);
        System.out.println("main <<< getTargetCopy1: " + node.toString());

        // **** get the target node from the clonned tree ****
        node = getTargetCopy(original.root, cloned.root, target);
        System.out.println("main <<<  getTargetCopy: " + node.toString());
    }

}