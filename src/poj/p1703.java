package poj;

import java.io.*;

public class p1703
{
    // 用来记录任务，其中0和MAX_N+1分别表示两个不同的帮派
    private int[] id;
    private int[] sz;
    private p1703(int n)
    {
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++)
        {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int find(int q)
    {   // 找到根节点
        if (q == id[q]) return q;
        return id[q] = find(id[q]);
    }

    // 合并两个节点
    private void union(int q, int p)
    {
        int qRoot = find(q);
        int pRoot = find(p);

        if (qRoot == pRoot) return;

        if (sz[qRoot] > sz[pRoot])
        {   // 将小树合并到大树
            id[pRoot] = qRoot;
        }
        else
        {
            id[qRoot] = pRoot;
            if (sz[qRoot] == sz[pRoot]) sz[pRoot]++;    // 增加树的数量
        }
    }

    private boolean connected(int q, int p)
    {
        return find(q) == find(p);
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String a = "Not sure yet.";
        String b = "In different gangs.";
        String c = "In the same gang.";

        int t = Integer.parseInt(in.readLine());
        while (t-- > 0)
        {
            String[] strs = in.readLine().split("\\s+");
            int n = Integer.parseInt(strs[0]);
            int m = Integer.parseInt(strs[1]);

            p1703 uf = new p1703(2*n+1);

            while (m-- > 0)
            {
                strs = in.readLine().split("\\s+");
                int q = Integer.parseInt(strs[1]);
                int p = Integer.parseInt(strs[2]);
                if (strs[0].equals("A"))
                {
                    if (uf.connected(p, q))
                    {
                        System.out.println(c);
                    }
                    else if (uf.connected(p, q+n))
                    {
                        System.out.println(b);
                    }
                    else
                    {
                        System.out.println(a);
                    }
                }
                else if (strs[0].equals("D"))
                {
                    uf.union(p, q+n);
                    uf.union(q, p+n);
                }
            }
        }
    }
}
