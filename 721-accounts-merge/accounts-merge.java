import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parent = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        // Step 1: Initialize
        for (List<String> acc : accounts) {
            String name = acc.get(0);
            for (int i = 1; i < acc.size(); i++) {
                parent.putIfAbsent(acc.get(i), acc.get(i));
                emailToName.put(acc.get(i), name);
            }
        }

        // Step 2: Union emails in same account
        for (List<String> acc : accounts) {
            String firstEmail = acc.get(1);
            for (int i = 2; i < acc.size(); i++) {
                union(parent, firstEmail, acc.get(i));
            }
        }

        // Step 3: Group emails by root
        Map<String, TreeSet<String>> groups = new HashMap<>();

        for (String email : parent.keySet()) {
            String root = find(parent, email);
            groups.putIfAbsent(root, new TreeSet<>());
            groups.get(root).add(email);
        }

        // Step 4: Build result
        List<List<String>> result = new ArrayList<>();
        for (String root : groups.keySet()) {
            List<String> merged = new ArrayList<>();
            merged.add(emailToName.get(root));
            merged.addAll(groups.get(root));
            result.add(merged);
        }

        return result;
    }

    private String find(Map<String, String> parent, String x) {
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent, parent.get(x)));
        }
        return parent.get(x);
    }

    private void union(Map<String, String> parent, String x, String y) {
        String rootX = find(parent, x);
        String rootY = find(parent, y);
        if (!rootX.equals(rootY)) {
            parent.put(rootY, rootX);
        }
    }
}