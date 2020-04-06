package bataranage006;

import java.util.ArrayList;
import java.util.List;

public class InspirationList {

    private ArrayList<Inspiration> inspirations;

    public InspirationList(){

        inspirations = new ArrayList<>();
        inspirations.add(new Inspiration(0,"Herbert Hoover","Where there is a lack of honor in government, the morals of the whole people are poisoned."));
        inspirations.add(new Inspiration(1,"Aristotle","The least initial deviation from the truth is multiplied later a thousandfold."));
        inspirations.add(new Inspiration(2,"Mark Twain","Everyone is a moon and has a dark side which he never shows to anybody."));
        inspirations.add(new Inspiration(3,"Calvin Coolidge","The right thing to do never requires any subterfuge, it is always simple and direct."));
        inspirations.add(new Inspiration(4,"Adolf Hitler","The victor will never be asked if he told the truth."));
        inspirations.add(new Inspiration(5,"Frank Tyger","Swallow your pride occasionally, it's not fattening."));
        inspirations.add(new Inspiration(6,"Henry Ford","History is more or less bunk."));
        inspirations.add(new Inspiration(7,"Albert Einstein","The most incomprehensible thing about the world is that it is at all comprehensible."));
        inspirations.add(new Inspiration(8,"Chris Bowyer", "I'd rather hear an old truth than a new lie."));
        inspirations.add(new Inspiration(9,"Mark Twain","It is noble to be good; it is still nobler to teach others to be good - and less trouble."));
    }

    public ArrayList<Inspiration> getInspirationsList(){

        return inspirations;
    }

}
