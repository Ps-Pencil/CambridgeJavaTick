datatype 'a MList = NIL 
                | CONS of 'a * ('a MList ref);
fun addHead v R = R:= CONS(v,(ref (!R)));
fun addTail v R = 
        if !R = NIL
        then R:= CONS(v,ref (!R))
        else 
                let val CONS(hd,tail) = !R
                in addTail v tail
                end;
