package domain.spark;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;

public enum BooleanHelper implements Helper<Boolean>{

    isTrue{
        @Override
        public CharSequence apply(Boolean arg0, Options arg1) throws IOException {
            if (arg0)
                return "checked";
            else
                return "notchecked";
        }
    }

}
