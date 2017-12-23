package aoj;

import java.util.*;

public class a2170
{
    private static final int MAX_N = 100000 + 16;
    private static int[] id = new int[MAX_N];   // 用来保存并查集的编号
    private static int[] sz = new int[MAX_N];   // 用来保存并查集树的大小
    private static int[] parentTree = new int[MAX_N];   // 用来保存父节点
    private static int[] ancestors = new int[MAX_N];     // 用来保存最近的染色的父节点
    private static boolean[] marked = new boolean[MAX_N];   // 用来保存节点的染色状况
    private static ArrayList<ArrayList<Integer>> childrenTree = new ArrayList<>(MAX_N);    // 用来保存子节点
    private static Stack<String> ops = new Stack<String>();                     // 用来保存操作
    private static Stack<Integer> point = new Stack<Integer>();                 // 用来保存被操作的数

    //======================================================
    // 并查集用到的操作
    private static void init(int n)
    {
        for (int i = 0; i < n; i++)
        {
            id[i] = i;
            sz[i] = 0;
            childrenTree.add(new ArrayList<>());
        }
    }

    private static int find(int p)
    {
        if (id[p] == p) return p;
        return  id[p] = find(id[p]);
    }

    private static void union(int p, int q)
    {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        if (sz[pRoot] > sz[qRoot])
        {
            id[qRoot] = pRoot;
        }
        else
        {
            id[pRoot] = qRoot;
            if (sz[pRoot] == sz[qRoot]) sz[qRoot]++;
        }
    }
    //====================================================

    // 利用广度优先更新染色的父节点
    private static void bsf(int index, int ancestor)
    {
        Queue<Integer> qIndex = new LinkedList<Integer>();
        Queue<Integer> qAncestor = new LinkedList<Integer>();

        qIndex.add(index);
        qAncestor.add(ancestor);

        while (!qIndex.isEmpty())
        {
            index = qIndex.poll();
            ancestor = qAncestor.poll();

            if (marked[index])
            {
                ancestor = index;   // 如果该节点已染色，则更新其父节点
            }
            ancestors[index] = ancestor;
            for (int i: childrenTree.get(index))
            {
                qIndex.add(i);
                qAncestor.add(ancestor);
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        while (true)
        {
            int n = in.nextInt();
            int m = in.nextInt();
            if (n == m && n == 0) break;

            // 创建树
            init(n);
            for (int i = 1; i < n; i++)
            {
                int q = in.nextInt();
                q--;

                childrenTree.get(q).add(i);
                parentTree[i] = q;

            }
            marked[0] = true;
            for (int i = 0; i < m; i++)
            {
                String q = in.next();
                int k = in.nextInt(); k--;

                if (q.equals("M"))
                {
                    if (marked[k]) continue;
                    marked[k] = true;
                }

                ops.push(q);
                point.push(k);
            }
            bsf(0, 0);
            for (int i = 0; i < n; i++)
            {   // 将各个节点与其父节点连接，方便查找
                union(i, ancestors[i]);
            }
            long sum = 0;
            while (!ops.isEmpty())
            {
                String q = ops.pop();
                int k = point.pop();
                if (q.equals("Q"))
                {   // 找到其根节点
                    sum += ancestors[find(k)] + 1;  // 题目初始节点为1
                }
                else
                {   // 褪色过程，将其祖先变成其父节点的祖先
                    int p = ancestors[find(parentTree[k])];
                    union(k, parentTree[k]);
                    ancestors[find(k)] = p;
                }
            }
            System.out.println(sum);
        }
    }
}
