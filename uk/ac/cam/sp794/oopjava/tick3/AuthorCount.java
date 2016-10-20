package uk.ac.cam.sp794.oopjava.tick3;

public class AuthorCount implements Comparable<AuthorCount> {
        private String author;
        private int count;

        public String getAuthor(){ return author;}
        public int getCount(){ return count;}

        public AuthorCount(String author) {
                this.author = author;
                this.count = 1;
        }
        public void inc() {
                count++;
        }
        @Override
        public int hashCode() {
                //TODO: Uniquely identify this object based on author name alone.
                char[] letters = this.author.toLowerCase().toCharArray();
                // assuming author name only contains alphabets and space
                int result = 0;
                int currentWeight = 1;
                for(int i = 0; i<letters.length;i++){
                        if(letters[i] == ' ')
                                result += currentWeight * 26;
                        else{
                                result += currentWeight * (letters[i]-'a');
                                currentWeight *= 27;
                        }
                }
                return result;
        }
        @Override
        public boolean equals(Object obj) {
                //TODO: return true iff obj is an AuthorCount object
                //      and this.author equals obj.author.
                //Hint: You might like to investigate what the 'instanceof' operator does.
                return (obj instanceof AuthorCount) && (this.author == ((AuthorCount)obj).getAuthor());
        }
        @Override
        public int compareTo(AuthorCount o) {
                //TODO: return 1 if 'this' rank orders before 'o',
                //      return 0 if 'this' and 'o' are equal, and -1 otherwise.
                //      to do so order first by this.count, and if counts are equal,
                //      then order by author.count as a tie-breaker.
                if(this.equals(o) && this.getCount() == o.getCount())
                        return 0;
                else{
                        if(this.count == o.getCount())
                                // <0 means author < o.author which means comes first
                                return this.author.compareTo(o.getAuthor());
                        else
                                return this.count > o.getCount() ? -1 : 1;
                }
        }
        @Override
        public String toString() {
                //TODO: return a string whose:
                //      first 20 characters contains the author name (left-aligned)
                //      and final 3 characters contains the count (right-aligned)
                //Hint: You might find String.format(...) helpful here.
                return String.format("%-20s%3d",this.author,this.count);
        } 
}
