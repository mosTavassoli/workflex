package com.workflex.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetMinimumDifferenceTest {

    private TreeNode n(int val, TreeNode left, TreeNode right) {
        return new TreeNode(val, left, right);
    }

    @Test
    void getMinimumDifference_basic_bst() {
        TreeNode root =
                n(4,
                        n(2, n(1, null, null), n(3, null, null)),
                        n(6, null, null)
                );

        GetMinimumDifference solver = new GetMinimumDifference();

        assertEquals(1, solver.getMinimumDifference(root));
    }

    @Test
    void getMinimumDifference_two_nodes() {
        TreeNode root = n(1, null, n(3, null, null));

        GetMinimumDifference solver = new GetMinimumDifference();

        assertEquals(2, solver.getMinimumDifference(root));
    }

    @Test
    void getMinimumDifference_skewed_tree() {
        TreeNode root =
                n(1, null,
                        n(2, null,
                                n(5, null,
                                        n(9, null, null)
                                )
                        )
                );

        GetMinimumDifference solver = new GetMinimumDifference();

        assertEquals(1, solver.getMinimumDifference(root));
    }

    @Test
    void getMinimumDifference_single_node() {
        TreeNode root = n(10, null, null);

        GetMinimumDifference solver = new GetMinimumDifference();

        assertEquals(0, solver.getMinimumDifference(root));
    }

    @Test
    void getMinimumDifference_with_negative_values() {
        TreeNode root =
                n(0,
                        n(-3, null, null),
                        n(9, null, null)
                );

        GetMinimumDifference solver = new GetMinimumDifference();

        assertEquals(3, solver.getMinimumDifference(root));
    }


}

