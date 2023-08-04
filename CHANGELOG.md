# CHANGELOG

## v1.1.0

- Blocks
    - Added PLACABLE blocks!!
        - when `Enable MetaTileEntity Blocks` is true
            - default: false
    - Unified textures of all material storage block
- Config
    - Renamed `Misc` -> `Material`
- Creative Tabs
    - Added `RMCreativeTabs.MATERIAL_BLOCK`
    - Renamed `RMCreativeTabs.MATERIAL` -> `RMCreativeTabs.MATERIAL_ITEM`
- Developing Environment
    - Migrated to [GregTechCEu/BuildScripts](https://github.com/GregTechCEu/Buildscripts)
- Material
    - Added `MaterialRegistryEvent` for registering HiiragiMaterial
    - Fixed problem: `MaterialType.GEM_4xADVANCED` not including `gem`
    - Remove duplicated shapes from vanilla materials
        - When `Disable Vanilla Parts` is true
- Shape
    - Added `ShapeReistryEvent` for registering HiiragiShape
    - Moved HiiragiShape fields from `ShapeRegistry` to `HiiragiShapes`

## v1.0.0

- The first official release!