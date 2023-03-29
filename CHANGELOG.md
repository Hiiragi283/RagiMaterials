# CHANGELOG

## v0.6.1b

- Fixed [#3](https://github.com/Hiiragi283/RagiMaterials/issues/3)
- Fixed [#4](https://github.com/Hiiragi283/RagiMaterials/issues/4)
- Fixed incorrect text in crafttweaker.md

## v0.6.0b

- Added GUI for Labo Table, I.C.R.C and Stone Mill
- Added integration for CraftTweaker
  - See [crafttweaker.md](https://github.com/Hiiragi283/RagiMaterials/blob/master/crafttweaker.md) for more details
- Fixed the duplicate bug about device recipes
- Refined registration for JEI tabs

### Blocks

- Oredict Converter: added new conversion rule; ragi_materials -> minecraft
- Stone Mill: added new recipes

### Items

- Crushed Vanilla Ore: crushed ore for vanilla ores

### Materials

- Prevent registering new materials with duplicated index or name, or negative index
- Reworked ore system

- New materials added: 
  - Lapis
  - Redstone
  - Glowstone
  - Ender Pearl
- Changed properties: 
  - Glass, Quartz -> colored white
  - Gallium, Arsenic -> added RARE rarity

## v0.5.0b

### << First Beta Release !!! >>

- Added issue templates for bug report
- Added new classes to create recipes for Forge Furnace and Stone Mill (Preparation for Crafttweaker...?)
- Bolded textural contours

### Blocks

- Coal/Lignite/Peat Soil:** **Fixed and refined behaviour
- Forge Furnace: multiplied Fuel Point by 200 to match the combustion time.
- I.C.R.C: Fix the duplicate bug
- Labo Table: Fix the synchronisation processing after first chemical reaction
- Ore Block: Harvested from Rainbow ore
- Salt Pond: Removed for its unstable behavior

### Items

- Crushed Ore: intermediate parts
- Forge Hammer: Restored!
- Ore Item: Replaced with Ore Block

### Materials

- Fix the bug that fluids were not registered
- **REWROTE ALL MATERIALS with RagiMaterial System**

## v0.4.1a

- Added issue templates for crash report
- Fixed the bug that blocks and items were incorrectly registered in creative tabs
- Removed unused model files

### Blocks

- [WIP] Ore Block: placable.
- Coal/Lignite Soil: Upgraded version of Peat Soil
- Stone Mill: obtain dusts from material parts

- Improved the behavior of Industrial Chemical Reaction Chamber
- Some functional blocks shows the result of actions

### Materials

- [WIP] NEW MATERIAL SYSTEM _RagiMaterial_ IS COMING SOON...
- Added new materials
    - Graphite
    - Nitratine
- Redesigned the textures for Crystals

## v0.4.0a

- Added new JEI tab for Laboratory Table
- The name of fluid uses material name instead of its own name

### Config values

- Changing max stack size: causing many problems
- Custom Material Registry: this will be registered by using Crafttweaker in the future
- Custom recipe for Forge Furnace: due to redesign of Forge Furnace
- Max Materials: expanded to 32767

### Blocks

- Bellow Block: removed due to redesign of Forge Furnace
- Blaze Heater: replaced with Blazing Forge
- Blazing Forge: upgrade version of Forge Furnace, using lava or other fluids for fuel
- Coal/Lignite/Peat Crop: replaced with Peat Soil
- Forge Furnace: redesigned for using tile entity
- Full Bottle Station: holding internal data when harvested or placed
- [WIP] Industrial Chemical Reaction Chamber (ICRC): processing chemical reaction recipes automatically
- Peat Soil: used for cultivating peat
- Rainbow Ore: drops various ores when mined

### Items

- Coal/Lignite/Peat Seed: removed due to redesign of Fossil Crops
- Forge Hammer: removed due to redesign of Material Tool System

### Materials

- Added new materials
    - Magnesium Carbonate
    - Alumina Solution
- Added new textures for material block
- Added rarity system
    - EPIC
        - Deuterium
        - Tritium
    - RARE
        - Helium
        - Neon
        - Argon
        - Titanium
        - Stainless Steel
        - Tool Steel
    - UNCOMMON
        - Steel
- Fix the bug that the molar mass of all materials is not calculated correctly
- Fix the incorrect weight of chlorine for Magnesium Chloride
- Unified metal blocks and crystal blocks into "material block"

## v0.3.1

- Added credits for model files
- Added new block "Full Bottle Station": making full bottle from fluids
- Added new interface: IMaterialItem & IMaterialBlock to handle materials more easily
- Removed material tooltips with Ore Dictionary or fluids
- Using TESR for Laboratory Table to show the contents of its inventory

## v0.3.0

- **REWORKED MATERIAL REGISTRATION**
- Added new crafting recipe to get Full Bottle from any other fluid container
- Changed Full Bottle behaviour from reusable to disposable
- Edited translation
- Removed heating recipes for Forge Furnace due to rewriting material registration temporarily
- Removed "minecraft:lava_bucket" and "minecraft:water_bucket" from default values in config

### New Blocks

- Laboratory Table: used for chemical reaction recipes

### New Item

- Chemical Waste: Failure product for chemical reaction recipe
- Ore / Nether Ore / End Ore

## v0.2.2

- Added integration option for config
- Added Logo and its license
- Fixed the fluid model bug when playing with Thermal Foundation
- Fixed the tooltips about elemental materials were not shown
- Removed the item forge:bucketfilled from config list

### Full Bottle

- Added new creative tab for Full Bottle
- Now only accepts material fluids
- Salt Pond no longer accepts Full Bottle

### **MATERIAL SYSTEM**

- Added alloy materials
    - Invar
    - Magnet
    - Stainless Steel
    - Steel
    - Tool Steel
    - Constantan
    - Brass
    - Bronze
    - Electrum
    - Tungsten Steel
- Added integration materials for Thermal Foundation
    - Mithril
    - Signalum
    - Lumium
    - Enderium
- Added new fields for:
    - Alternative Ore Dictionary Name
    - Burn Time
    - Decayed Material
- Added radioactive decay system (Configurable)
    - Renamed some radioactive materials
- Made MaterialType more flexible
- Reworked the model for Hot Ingot

## v0.2.1

- Updated CHANGELOG
- Added CHANGELOG.txt
  in [resource folder](https://github.com/Hiiragi283/RagiMaterials/blob/master/src/main/resources/CHANGELOG.txt)

## v0.2.0

### New Blocks

- [WIP] Peat/Lignite/Coal Crop: designed for peat farming

### New Items

- Block of ** crystal: used for crystalline materials
- Crystal: used for crystalline materials
- Gear: used for metallic materials
- Stick: used for metallic materials
- [WIP] Peat/Lignite/Coal Seed: designed for peat farming

### **REWORKED MATERIAL SYSTEM**

- Added ElementBuilder to split elements and simple
  substances [#2](https://github.com/Hiiragi283/RagiMaterials/issues/2)
- Added material name to material tooltips
- Added MixtureBuilder to register mixture materials
    - Used for non-metallic solid materials: brick, ice, etc
- Added new MaterialType: INGOT
- Added Vanilla materials
- Fixed CompoundBuilder generate incorrect molar mass
- Material tooltips now are generated from ore dictionary
- Registered recipes for appropriate materials
- Removed BlockFluid
- Removed existing alloy and crystalline materials entirely
- Renamed some variables in MaterialBuilder
- Used hashmap instead of list to get materials from given index or name
    - Thanks to _[kobi32768](https://github.com/kobi32768)_ !
      and _[Sayamame-beans](https://github.com/Sayamame-beans)_ !
- Variables in MaterialBuilder now accepts null value

### Other Changes

- BlockSaltPond
    - Accepts any fluid container
    - Better block connection
- Fixed zero division in RagiColor.mixColor
- Refined ore dictionary registration

## v0.1.9

- Added [CHANGELOG.md](https://github.com/Hiiragi283/RagiMaterials/blob/master/CHANGELOG.md)

### New Items

- Full Bottle: Can hold 1000 mb of any fluid
- Tiny Pile of xx: 1/9 Dust

### Other Changes

- Added new material type: crystal and crystalline materials
- Reworked material textures
- Salt Pond Block can connect any other blocks
- Using Capability to get fluid property from any fluid container