// Problem 269. Alien Dictionary
// Time Complexity : O(N×L+QXL)
// Space Complexity : O(N×L)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
class StreamChecker {
    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;

        public TrieNode() {
            children = new HashMap<>();
            isWord = false;
        }
    }
    private TrieNode root;
    private Deque<Character> stream;
    private int maxLength;
    public StreamChecker(String[] words) {
        root = new TrieNode();
        stream = new LinkedList<>();
        maxLength = 0;
        for (String word : words) {
            maxLength = Math.max(maxLength, word.length());
            TrieNode node = root;
            for (int i = word.length() - 1; i >= 0; i--) {
                char c = word.charAt(i);
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
            }
            node.isWord = true;
        }
    }
    
    public boolean query(char letter) {
        stream.addFirst(letter);
        if (stream.size() > maxLength) {
            stream.removeLast();
        }
        TrieNode node = root;
        for (char c : stream) {
            if (!node.children.containsKey(c)) {
                return false;
            }
            node = node.children.get(c);
            if (node.isWord) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */