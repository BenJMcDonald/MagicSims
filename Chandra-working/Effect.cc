/* Well, this is rather abstract. This is how I've decided
to implement cards that resolve different things.

An effect is a virtual class, which contains a single method
resolve(LList targets). This method represents performing the action. Effect
is extended by various classes that represen specific types
of effects (i.e. damage) and then instantianted to represent
an amound of that effect.

A particular card will have a pointer to an effect object,
and is cast with a linked list of targets. When the card is cast,
it is given this list of targets. It then passes it to the Effect
object, which resolves to a particular effect, which then performs
the appropriate action on the targets.

For example, Damage extends Effect. Searing spear will instantiate
a damage object with the quantity = 3, so when searing spear calls 
->resolve(targets) on its Effect object, each target is dealt three damage.

If the best implementation of a card is multiple basic effects, then  
this is resolved by using a 'multiple' effect, which is instantiated
by giving it several other basic effects, and so when it is called
it simply calls all of these events in sequence. It takes
a list of lists for its target and passes the first list to the
first effect, and so on. I suppose this could be used recursively,
but I don't see much point in doing that.
*/

class Effect{
    //return false if the effect failed
    //i.e. countered on resolution
    bool resolve(LList<player*>*) = 0;
}


