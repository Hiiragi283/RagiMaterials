# CraftTweaker Integration

---

## Bracket Handler

### Element

~~~zenscript
val element = <element:name>;
val hydrogen = <element:hydrogen>;
~~~

### Material

~~~zenscript
val material = <material:name>;
val steel = <material:steel>;
~~~

---

## MaterialUtil

`import mods.ragi_materials.MaterialUtil`

~~~zenscript
MaterialUtil.getIndex(material as RagiMaterial) as Int
MaterialUtil.getName(material as RagiMaterial) as String
MaterialUtil.getType(material as RagiMaterial) as String
MaterialUtil.getBurnTime(material as RagiMaterial) as Int
MaterialUtil.getColor(material as RagiMaterial) as Int
MaterialUtil.getCrystalType(material as RagiMaterial) as String
MaterialUtil.getDecayed(material as RagiMaterial) as RagiMaterial
MaterialUtil.getFormula(material as RagiMaterial) as String
MaterialUtil.getMolar(material as RagiMaterial) as Float
MaterialUtil.getRarity(material as RagiMaterial) as String
MaterialUtil.getTempMelt(material as RagiMaterial) as Int
MaterialUtil.getTempBoil(material as RagiMaterial) as Int

# Please use stringFormula(formula as String) in setComponents()
MaterialUtil.stringFormula(name as String) as RagiMaterial

val builder = MaterialBuilder(1026, "lol", "gas");
builder.setComponents({MaterialUtil.stringFormula("supercalifragilisticexpialidocious"): 1});
builder.build();

MaterialUtil.getFormula(<material:lol>) # return "supercalifragilisticexpialidocious"

# setBracket() is used to add bracket: () for existing materials
MaterialUtil.setBracket(material as RagiMaterial) as RagiMaterial

MaterialUtil.getFormula(MaterialUtil.setBracket(<material:steel>)); # return "(FeC)"
~~~

---

## MaterialBuilder

`import mods.ragi_materials.MaterialBuilder`

### Constructor

~~~zenscript
# index ... 0 ~ 32767, do not duplicate existing one
# name ... Used for lang key (material.name) and oredict name (~Name), do not duplicate existing one
# type ... crystal, dust, fuel, gas, ingot, internal, liquid, metal, metalloid, stone, wildcard, wood

val builder = MaterialBuilder(index as Int, name as String, type as String);
~~~

### Methods

~~~zenscript
builder.setBurnTime(200 * 8 as Int);
builder.setColor(0xFF003F as Int);
builder.setCrystalType("diamond" as String); # coal, cubic, diamond, emerald, lapis, quartz, ruby
builder.setDecayed(<material:lead> as RagiMaterial); # Used for specifying the material after radioactive decay
builder.setFormula("Rm" as String);
builder.setMolarMass(110.9f as Float);
builder.setOreDictAlt("Alternative" as String); # Used for register alternative oredict name
builder.setRarity("Rare" as String); # Common, Uncommon, Rare, Epic
builder.setTempMelt(283 as Int);
builder.setTempBoil(1109 as Int);
~~~

~~~zenscript
# The method setComponents() generates material color, chemical formula and molar mass from given map.
# If all of the map keys are metal materials, melting point and boiling point also generated.

builder.setComponents({<material:iron>: 1, <material:sulfur>: 2} as Int[RagiMaterial]);

# The method setMixture() generates mixture chemical formula: (A, B, C) and resets molar mass.
# This should be called after setComponents().

builder.setMixture();

# The method setSimple() is used to make the material simple substance.
# This is incompatible with setComponents() and setMixture().

builder.setSimple(<element:oxygen>, 3);
~~~

~~~zenscript
# Finally, you must call build() to register the material to RagiMaterial System.
# If the index is negative number, or the index or name duplicates with existing one, the material will not be registered.

builder.build();
~~~

---

## Class

### Labo Table

`import mods.ragi_materials.LaboTable`

~~~zenscript
mods.ragi_materials.LaboTable.addRecipe(name as String, outputs as IItemStack[], inputs as IItemStack[])
~~~

### Stone Mill

`import mods.ragi_materials.StoneMill`

~~~zenscript
mods.ragi_materials.StoneMill.addRecipe(name as String, output as IItemStack, input as IItemStack);
~~~