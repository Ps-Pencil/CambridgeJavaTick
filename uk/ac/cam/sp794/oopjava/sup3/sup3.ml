datatype 'a MTree = LF | BR of ('a) * ('a MTree ref) * ('a MTree ref);

fun countOdd (ref LF) = 0
  | countOdd (ref (BR(a,b,c))) = 
          a mod 2  + countOdd b + countOdd c

fun six_to_seven (ref LF) =()
  | six_to_seven (x as (ref (BR(a,b,c)))) = 
          (x := BR((if a = 6 then 7 else a),b,c); six_to_seven b; six_to_seven c);

(*Doesn't work for cyclic graph*)
fun count_path_100 a =
        let fun count (ref LF) sum = 
                if sum = 100
                then 1 
                else 0
              | count (ref (BR(a,b,c))) sum = 
                      count b (sum+a) + count c (sum+a)
        in count a 0
        end;

fun member x (y::ys) = (x=y) orelse ( member x ys)
  | member _ [] = false;
(* assume path does not contain same node twice *)
fun count_path_cyclic a =
        let fun count (ref LF) visited sum = 
                if sum = 100
                then 1 
                else 0
             |  count (x as (ref (BR(a,b,c)))) visited sum= 
                     if member x visited
                     then 0
                     else count b (x::visited) (sum + a) + count c (x::visited) (sum + a)
        in count a [] 0
        end;
        
          
         
          
