# CHANGELOG

## v1.3.0 - Machine Update

### Updated Dependencies

- Removed `Mixinbooter` and `Modular UI`
- Added `Shadowfact's Forgelin`
  - Due to instability, Forgelin Bridge is discontinued

### Configs

- Removed values
  - `Disable Vanilla Parts`
  - `Generate Sample Json`: Moved to Common category
  - `Print Registered Materials on Log`: Moved to Common category
- Added values
  - `Enable The One Probe`: Compat for The One Probe

- Changed directory for Json Configs
  - Adding shapes: `config/ragi_materials/shapes`
  - Adding materials: `config/ragi_materials/materials`
  - Adding recipes: `config/ragi_materials/recipes`

### Module Machines

- Added new block Module Machine: Customizable processing machine
- Added new item Recipe Module: Components that determine the type of machine
- Types
  - `Assembler`
  - `Cenrifuge`
  - `Compressor`
  - `Cutting Machine`
  - `Distiller`
  - `Electrolyzer`
  - `Freezer`
  - `Grinder`
  - `Metal Former`
  - `Mixer`
  - `Rock Generator`
  - `Smelter`

### Blocks

- Removed
  - `Crucible`: Unified into Module Machines
- Added
  - `Casing`: Core of Module Machines
  - `Machine Extender`: Add more face for front block
  - `Machine Workbench`: Used to craft Module Machines
  - `Capability Rail`: Read capabilities from entities on the rail
  - `Transfer Pipe`: Not a pipe like other mods, but a guide for `Transfer Station`
  - `Transfer Station`: Import Item/Fluid/Energy from back, Transfer it to the terminal of `Transfer Pipes`

### Entities

- Added (Experimental)
  - `Minecart with Tank`: 16,000 mB of tank capacity, interact with Right-Clicking or `Capability Rail`

### Items

- Removed
  - `Tiny Dusts`: There's no usage in this mod
  - `Crushing Hammer`: Replaced with `Smithing Hammer`
  - `Clay/Unfired Cast`: Replaced with `Shape Pattern`
- Added
  - `Shape Pattern`: Used for Casting or Metal Forming recipes
  - `Smithing Hammer`: Not only transform material items, but also rotate blocks
  - `[WIP] Motor`: Experimental item for upgrading Module Machines 

### Materials

- Added
  - `Raw Steel`

### Shapes

- Added
  - `Casing`
  - `Frame`

### Technical Changes

- Updated `build.gradle`
- MOVED MAIN PACKAGE FROM `hiiragi283` TO `hiiragi283/material`
- Removed core mod

## v1.2.1

### Updated Dependencies

- `Forgelin-Continuous`
  - Revived to match the version of the kotlin library
- `Modular UI`

### Forgelin Bridge

- This function is to disguise Forgelin Continuous as Shadowfact's Forgelin. Therefore, other mods that requires
  Shadowfact's Forgelin can use Forgelin Continuous as an alternative.

### Blocks

- [WIP] `Rock Generator`
- [WIP] `Scaffolding`

### Fluids

- Revived Material Fluids
  - Only fluids that are not registered by other mods are implemented.

### Materials

- Added new materials
  - `Tungsten Steel`
- Added new properties
  - `Hardness`: affecting processing time
- Changed translation key format
  - `material.XX` -> `hiiragi_material.XX`
    - thanks to Roseyasa!
- Material Tooltips for Fluid Containers
  - Displays material properties from the fluid contained in the fluid container. (Bucket, Tank, ...)

### Shapes

- Added new shapes
  - `FENCE`
  - `SCAFFOLDING`
  - `SHEETMETAL`
  - `SLAB`
  - `SLAB_SHEETMETAL`
  - `WIRE`
- Changed translation key format
  - `shape.XX` -> `hiiragi_shape.XX`
    - thanks to Roseyasa!

## v1.2.0

### Unified RagiChemistry into RagiMaterials

- RagiMaterials supports various features based on the material system !!

### Updated Dependencies

- Removed
  - Forgelin-Continuous
    - from this version, this mod includes kotlin library
- Added
  - Mixinbooter

### New Features

- New creative tabs: COMMON
- Removed material fluids

#### Blocks

- Crucible: melts metallic materials and casts them into components

#### Items

- Unfired Cast & Cast: used for Crucible to convert MaterialStacks into ItemStacks

#### Materials

- Added melting / boiling / sublimation point for steel-like materials
  - the value is the same as iron
- Added new object `MaterialStack`
  - consisting of `HiiragiMaterial` and its quantity (Integer)
  - Similar to `FluidStack` but more suitable for the material system
- Fixed incorrect material info for grinding balls (Ender IO)

#### Shapes

- Changed the type of `HiiragiShape.scale` from Double to Integer
  - this value is the same as fluid amount
    - Example: a scale of ingot is 144

### Integration

#### Had Enough Items

- Added new category
  - Crucible
  - Crushing (Maybe removed in next update)
  - Heat Source (Maybe removed in next update)
  - Material
- Added new ingredient: `MaterialStack`

### For Devs

- Unified some registry classes into `HiiragiRegistry`
- New Capability `IMaterialHandler`
  - Similar to `IFluidHander`
- Item Interface `ICastItem`
  - Used for Crucible Casting Recipe
- Added new function `alloyOf()` to create alloy materials
- Added core mod and mixin (current not used)
- Added new contributor: [yuzu_machan](https://github.com/yuzu-machan)

## v1.1.0

### Blocks

- Added PLACABLE blocks!!
  - when `Enable MetaTileEntity Blocks` is true
    - default: false
- Unified textures of all material storage block

### Config

- Renamed `Misc` -> `Material`

### Creative Tabs

- Added `RMCreativeTabs.MATERIAL_BLOCK`
- Renamed `RMCreativeTabs.MATERIAL` -> `RMCreativeTabs.MATERIAL_ITEM`

### Developing Environment

- Migrated to [GregTechCEu/BuildScripts](https://github.com/GregTechCEu/Buildscripts)

### Material

- Added `MaterialRegistryEvent` for registering HiiragiMaterial
- Fixed problem: `MaterialType.GEM_4xADVANCED` not including `gem`
- Remove duplicated shapes from vanilla materials
  - When `Disable Vanilla Parts` is true

### Shape

- Added `ShapeReistryEvent` for registering HiiragiShape
- Moved HiiragiShape fields from `ShapeRegistry` to `HiiragiShapes`

## v1.0.0

### The first official release!