package hiiragi283.material.api.capability;

import org.jetbrains.annotations.NotNull;

public interface IOControllable {

    @NotNull
    Type getIOType();

    enum Type {

        INPUT(true, false),
        OUTPUT(false, true),
        GENERAL(true, true),
        CATALYST(false, false);

        public final boolean canInsert, canExtract;

        Type(boolean canInsert, boolean canExtract) {
            this.canInsert = canInsert;
            this.canExtract = canExtract;
        }

    }

}