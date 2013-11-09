package com.hexfaker.exprproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Programer: Poletaev Vsevolod @ CGSG'12
 * Created at: 09.11.13
 * Package: com.hexfaker.exprproc
 * Description: Variable server class
 */
public class VariableServer {
    private final static class VarListItem {
        public final int mNo;
        public final String mName;
        public double mVal;
        public final int mNameHash;

        public VarListItem(int No, String Name, double StartVal) {
            mNo = No;
            mName = Name;
            mVal = StartVal;
            mNameHash = mName.hashCode();
        }

        public VarListItem(int No, String Name) {
            mNo = No;
            mName = Name;
            mVal = 0;
            mNameHash = mName.hashCode();
        }
    }

    private List<VarListItem> mVarList = new ArrayList<VarListItem>();

    // Get variable by name method
    public Variable getVar(String Name) {
        final int hash = Name.hashCode();
        for (int i = 0; i < mVarList.size(); i++)
            if (mVarList.get(i).mNameHash == hash)
                return new Variable(i, this);
        mVarList.add(new VarListItem(mVarList.size(), Name));
        return new Variable(mVarList.size() - 1, this);
    }

    public double getVarValue()
}
