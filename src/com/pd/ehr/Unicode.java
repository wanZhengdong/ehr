package com.pd.ehr;

import com.pd.EhrApi.Builder.IBuilder;

public class Unicode
{
    public static class Builder
    {
        public static class ToCn implements IBuilder<String, String>
        {
            
            @Override
            public String build(String _in)
            {
                // TODO Auto-generated method stub
                return decode(_in);
            }
            
            private static String decode(String unicodeStr)
            {
                if (unicodeStr == null)
                {
                    return null;
                }
                StringBuffer retBuf = new StringBuffer();
                int maxLoop = unicodeStr.length();
                for (int i = 0; i < maxLoop; i++)
                {
                    if (unicodeStr.charAt(i) == '\\')
                    {
                        if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                            try
                            {
                                retBuf.append((char)Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                                i += 5;
                            }
                            catch (NumberFormatException localNumberFormatException)
                            {
                                retBuf.append(unicodeStr.charAt(i));
                            }
                        else
                            retBuf.append(unicodeStr.charAt(i));
                    }
                    else
                    {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                }
                return retBuf.toString();
            }
        }
    }
}